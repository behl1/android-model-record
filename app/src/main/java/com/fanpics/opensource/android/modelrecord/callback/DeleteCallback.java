package com.fanpics.opensource.android.modelrecord.callback;

import com.fanpics.opensource.android.modelrecord.HttpReport;
import com.fanpics.opensource.android.modelrecord.RecordCache;
import com.fanpics.opensource.android.modelrecord.configuration.SingleRecordConfiguration;
import com.squareup.otto.Bus;

public class DeleteCallback<T> extends RecordCallback<T> {

    protected DeleteCallback(SingleRecordConfiguration<T> configuration, Bus bus, HttpReport httpReport, T model) {
        super(configuration, bus, httpReport);
        setKey(model);
    }

    public static <T> DeleteCallback<T> createFromConfiguration(SingleRecordConfiguration<T> configuration, Bus bus, HttpReport httpReport,
                                                                T model){
        return new DeleteCallback<>(configuration, bus, httpReport, model);
    }

    @SuppressWarnings("unchecked")
    protected void manageCacheIfExists(T model) {
        final RecordCache<T> cache = configuration.getCache();
        if (cache != null) {
            final T modelToDelete = (T) getKey();
            cache.delete(modelToDelete);
        }
    }
}
