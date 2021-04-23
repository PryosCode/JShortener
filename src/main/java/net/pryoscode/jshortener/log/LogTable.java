package net.pryoscode.jshortener.log;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class LogTable {

    private static final int PADDING = 5;

    private final String[] headers;
    private List<String[]> rows = new ArrayList<>();

    public LogTable(String... headers) {
        this.headers = headers;
    }

    public void addRow(String... columns) {
        rows.add(columns);
    }

    private String spacer() {
        StringBuilder builder = new StringBuilder();
        builder.append("+");
        for (int i = 0; i < headers.length; i++) {
            for (int j = 0; j < getColumnSize(i) + PADDING; j++)
                builder.append("-");
            builder.append("+");
        }
        return builder.toString();
    }

    private String formatRow(String[] row) {
        StringBuilder builder = new StringBuilder();
        builder.append("|");
        for (int i = 0; i < row.length; i++)
            builder.append(StringUtils.center(row[i], getColumnSize(i) + PADDING)).append("|");
        return builder.toString();
    }

    private int getColumnSize(int column) {
        int size = headers[column].length();
        for (String[] row : rows)
            if (row[column].length() > size)
                size = row[column].length();
        return size;
    }

    public void print() {
        Log.info(spacer());
        Log.info(formatRow(headers));
        Log.info(spacer());
        for (String[] row : rows)
            Log.info(formatRow(row));
        Log.info(spacer());
    }

}