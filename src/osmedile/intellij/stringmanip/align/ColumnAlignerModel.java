package osmedile.intellij.stringmanip.align;

import com.intellij.util.xmlb.annotations.Transient;
import osmedile.intellij.stringmanip.sort.support.SortSettings;
import shaded.org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ColumnAlignerModel {
    private Date added;
    private List<String> separators = new ArrayList<String>();
    private boolean spaceBeforeSeparator = true;
    private boolean spaceAfterSeparator = true;
    private boolean trimValues = true;
    private boolean trimLines = false;
    private Align alignBy = Align.SEPARATORS;
    private boolean sequentialProcessing;
    private SortSettings sortSettings = new SortSettings();
    private String columnSortOrder;
    private boolean skipFirstRow;
    private boolean sortOnly;
    private String maxSeparatorsPerLine;

    public ColumnAlignerModel() {
    }

    public ColumnAlignerModel(String... separator) {
        this.separators = Arrays.asList(separator);
    }

    public List<String> getSeparators() {
        return separators;
    }

    public void setSeparators(List<String> separators) {
        this.separators = separators;
    }

    public boolean isSpaceBeforeSeparator() {
        return spaceBeforeSeparator;
    }

    public void setSpaceBeforeSeparator(final boolean spaceBeforeSeparator) {
        this.spaceBeforeSeparator = spaceBeforeSeparator;
    }

    public boolean isSpaceAfterSeparator() {
        return spaceAfterSeparator;
    }

    public void setSpaceAfterSeparator(final boolean spaceAfterSeparator) {
        this.spaceAfterSeparator = spaceAfterSeparator;
    }

    public boolean isTrimValues() {
        return trimValues;
    }

    public void setTrimValues(final boolean trimValues) {
        this.trimValues = trimValues;
    }

    public boolean isTrimLines() {
        return trimLines;
    }

    public void setTrimLines(final boolean trimLines) {
        this.trimLines = trimLines;
    }

    public Align getAlignBy() {
        return alignBy;
    }

    public void setAlignBy(Align alignBy) {
        this.alignBy = alignBy;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public boolean isSequentialProcessing() {
        return sequentialProcessing;
    }

    public void setSequentialProcessing(final boolean sequentialProcessing) {
        this.sequentialProcessing = sequentialProcessing;
    }

    public SortSettings getSortSettings() {
        return sortSettings;
    }

    public void setSortSettings(SortSettings sortSettings) {
        this.sortSettings = sortSettings;
    }

    public String getColumnSortOrder() {
        return columnSortOrder;
    }

    public void setColumnSortOrder(final String columnSortOrder) {
        this.columnSortOrder = columnSortOrder;
    }

    public boolean isSkipFirstRow() {
        return skipFirstRow;
    }

    public void setSkipFirstRow(final boolean skipFirstRow) {
        this.skipFirstRow = skipFirstRow;
    }

    public boolean isSortOnly() {
        return sortOnly;
    }

    public void setSortOnly(final boolean sortOnly) {
        this.sortOnly = sortOnly;
    }

    public String getMaxSeparatorsPerLine() {
        return maxSeparatorsPerLine;
    }

    public void setMaxSeparatorsPerLine(final String maxSeparatorsPerLine) {
        this.maxSeparatorsPerLine = maxSeparatorsPerLine;
    }

    @Transient
    public Integer getMaxSeparatorsPerLineAsInt() {
        Integer integer = null;
        try {
            integer = Integer.valueOf(maxSeparatorsPerLine);
        } catch (Exception e) {
            integer = Integer.MAX_VALUE;
        }
        return integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnAlignerModel that = (ColumnAlignerModel) o;

        if (spaceBeforeSeparator != that.spaceBeforeSeparator) return false;
        if (spaceAfterSeparator != that.spaceAfterSeparator) return false;
        if (trimValues != that.trimValues) return false;
        if (trimLines != that.trimLines) return false;
        if (sequentialProcessing != that.sequentialProcessing) return false;
        if (skipFirstRow != that.skipFirstRow) return false;
        if (sortOnly != that.sortOnly) return false;
        if (added != null ? !added.equals(that.added) : that.added != null) return false;
        if (separators != null ? !separators.equals(that.separators) : that.separators != null) return false;
        if (alignBy != that.alignBy) return false;
        if (sortSettings != null ? !sortSettings.equals(that.sortSettings) : that.sortSettings != null) return false;
        if (columnSortOrder != null ? !columnSortOrder.equals(that.columnSortOrder) : that.columnSortOrder != null)
            return false;
        return maxSeparatorsPerLine != null ? maxSeparatorsPerLine.equals(that.maxSeparatorsPerLine) : that.maxSeparatorsPerLine == null;
    }

    @Override
    public int hashCode() {
        int result = added != null ? added.hashCode() : 0;
        result = 31 * result + (separators != null ? separators.hashCode() : 0);
        result = 31 * result + (spaceBeforeSeparator ? 1 : 0);
        result = 31 * result + (spaceAfterSeparator ? 1 : 0);
        result = 31 * result + (trimValues ? 1 : 0);
        result = 31 * result + (trimLines ? 1 : 0);
        result = 31 * result + (alignBy != null ? alignBy.hashCode() : 0);
        result = 31 * result + (sequentialProcessing ? 1 : 0);
        result = 31 * result + (sortSettings != null ? sortSettings.hashCode() : 0);
        result = 31 * result + (columnSortOrder != null ? columnSortOrder.hashCode() : 0);
        result = 31 * result + (skipFirstRow ? 1 : 0);
        result = 31 * result + (sortOnly ? 1 : 0);
        result = 31 * result + (maxSeparatorsPerLine != null ? maxSeparatorsPerLine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String format = "";
        if (added != null) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(added);
        }


        StringBuilder s = new StringBuilder();
        for (String separator : separators) {
            if (StringUtils.isNotEmpty(separator)) {
                s.append(separator).append(" ");
            }
        }
        return format + " - " + s.toString();
    }

    public enum Align {
        VALUES, SEPARATORS
    }
}