/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.plan.docente.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

/**
 *
 * @author 727855
 */
public class CSVExporter extends Exporter {

    @Override
    public void export(FacesContext context, DataTable table, String filename, boolean pageOnly, boolean selectionOnly, String encodingType, MethodExpression preProcessor, MethodExpression postProcessor) throws IOException {
        ExternalContext externalContext = context.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        Writer writer = response.getWriter();

        response.setContentType("text/csv; charset=" + encodingType);
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-disposition", "attachment;filename=" + filename + ".csv");

        addColumnFacets(writer, table, Exporter.ColumnType.HEADER);

        if (pageOnly) {
            exportPageOnly(context, table, writer);
        } else if (selectionOnly) {
            exportSelectionOnly(context, table, writer);
        } else {
            exportAll(context, table, writer);
        }

        if (table.hasFooterColumn()) {
            addColumnFacets(writer, table, Exporter.ColumnType.FOOTER);
        }

        writer.flush();
        writer.close();

        context.responseComplete();
    }

    private static final int PAGE_EXPORT_SIZE = 500;

    @Override
    protected void exportAll(FacesContext context, DataTable table, Object document) {
        int first = table.getFirst();
        int rowCount = table.getRowCount();
        int rows = table.getRows();
        int row = 0;
        int cicles = (rowCount / PAGE_EXPORT_SIZE);
        if ((rowCount % PAGE_EXPORT_SIZE) > 0) {
            cicles++;
        }
        boolean lazy = table.isLazy();

        if (lazy) {
            int lastRowPage;
            int firstRowPage;
            if (rowCount > 0) {
                for (int pageIndex = 0; pageIndex < cicles; pageIndex++) {
                    firstRowPage = (pageIndex * PAGE_EXPORT_SIZE);
                    lastRowPage = firstRowPage + PAGE_EXPORT_SIZE;
                    table.setFirst(pageIndex * PAGE_EXPORT_SIZE);
                    table.setRows(PAGE_EXPORT_SIZE);
                    table.clearLazyCache();
                    table.loadLazyData();

                    for (int rowIndex = firstRowPage; rowIndex < lastRowPage; rowIndex++) {
                        exportRow(table, document, rowIndex);
                    }
                }
            }

            //restore
            table.setFirst(first);
            table.setRowIndex(-1);
            table.clearLazyCache();
            table.loadLazyData();
        } else {
            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                exportRow(table, document, rowIndex);
            }

            //restore
            table.setFirst(first);
        }
    }

    protected void addColumnFacets(Writer writer, DataTable table, Exporter.ColumnType columnType) throws IOException {
        boolean firstCellWritten = false;

        for (UIColumn col : table.getColumns()) {
            if (col instanceof DynamicColumn) {
                ((DynamicColumn) col).applyStatelessModel();
            }

            if (col.isRendered() && col.isExportable()) {
                if (firstCellWritten) {
                    writer.write(";");
                }

                UIComponent facet = col.getFacet(columnType.facet());
                if (facet != null) {
                    addColumnValue(writer, facet);
                } else {
                    String textValue;
                    switch (columnType) {
                        case HEADER:
                            textValue = col.getHeaderText();
                            break;
                        default:
                            textValue = "";
                            break;
                    }
                    addColumnValue(writer, textValue);
                }
                firstCellWritten = true;
            }
        }
        writer.write("\n");
    }

    @Override
    protected void exportCells(DataTable table, Object document) {
        PrintWriter writer = (PrintWriter) document;
        boolean firstCellWritten = false;

        for (UIColumn col : table.getColumns()) {
            if (col instanceof DynamicColumn) {
                ((DynamicColumn) col).applyStatelessModel();
            }

            if (col.isRendered() && col.isExportable()) {
                if (firstCellWritten) {
                    writer.write(";");
                }

                try {
                    addColumnValue(writer, col.getChildren());
                } catch (IOException ex) {
                    throw new FacesException(ex);
                }

                firstCellWritten = true;
            }
        }
    }

    protected void addColumnValues(Writer writer, List<UIColumn> columns) throws IOException {
        for (Iterator<UIColumn> iterator = columns.iterator(); iterator.hasNext();) {
            addColumnValue(writer, iterator.next().getChildren());

            if (iterator.hasNext()) {
                writer.write(";");
            }
        }
    }

    protected void addColumnValue(Writer writer, UIComponent component) throws IOException {
        String value = component == null ? "" : exportValue(FacesContext.getCurrentInstance(), component);

        addColumnValue(writer, value);
    }

    protected void addColumnValue(Writer writer, String value) throws IOException {
        value = (value == null) ? "" : value.replaceAll("\"", "\"\"");

        writer.write("\"" + value + "\"");
    }

    protected void addColumnValue(Writer writer, List<UIComponent> components) throws IOException {
        writer.write("\"");

        for (UIComponent component : components) {
            if (component.isRendered()) {
                String value = exportValue(FacesContext.getCurrentInstance(), component);

                //escape double quotes
                value = value == null ? "" : value.replaceAll("\"", "\"\"");

                writer.write(value);
            }
        }

        writer.write("\"");
    }

    @Override
    protected String exportValue(FacesContext context, UIComponent component) {

        if (component instanceof HtmlCommandLink) {  //support for PrimeFaces and standard HtmlCommandLink
            HtmlCommandLink link = (HtmlCommandLink) component;
            Object value = link.getValue();

            if (value != null) {
                return String.valueOf(value);
            } else {
                //export first value holder
                for (UIComponent child : link.getChildren()) {
                    if (child instanceof ValueHolder) {
                        return exportValue(context, child);
                    }
                }

                return "";
            }
        } else if (component instanceof ValueHolder) {

            if (component instanceof EditableValueHolder) {
                Object submittedValue = ((EditableValueHolder) component).getSubmittedValue();
                if (submittedValue != null) {
                    return submittedValue.toString();
                }
            }

            ValueHolder valueHolder = (ValueHolder) component;
            Object value = valueHolder.getValue();

            if (value == null) {
                return "";
            }
            if (value.getClass().getName().equals(Date.class.getName())) {
                try {
                    value = FormatoFechasUtil.dateToString(FormatoFechasUtil.FORMATO_FECHA_HORA_PRESENTACION, (Date) value);
                } catch (ParseException ex) {
                    Logger.getLogger(CSVExporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (value.getClass().getName().equals(Timestamp.class.getName())) {
                try {
                    value = FormatoFechasUtil.dateToString(FormatoFechasUtil.FORMATO_FECHA_HORA_PRESENTACION, (Timestamp) value);
                } catch (ParseException ex) {
                    Logger.getLogger(CSVExporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            Converter converter = valueHolder.getConverter();
            if (converter == null) {
                Class valueType = value.getClass();
                converter = context.getApplication().createConverter(valueType);
            }

            if (converter != null) {
                return converter.getAsString(context, component, value);
            }

            //No converter found just return the value as string
            return value.toString();
        } else if (component instanceof CellEditor) {
            return exportValue(context, ((CellEditor) component).getFacet("output"));
        } else if (component instanceof HtmlGraphicImage) {
            return (String) component.getAttributes().get("alt");
        } else {
            //This would get the plain texts on UIInstructions when using Facelets
            String value = component.toString();

            if (value != null) {
                return value.trim();
            } else {
                return "";
            }
        }
    }

    @Override
    protected void postRowExport(DataTable table, Object document) {
        ((PrintWriter) document).write("\n");
    }
}
