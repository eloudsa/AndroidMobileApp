package com.devoxx.data.manager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

@EBean
public abstract class AbstractDataManager<T> {

	public interface IDataManagerListener<T> {
		void onDataStartFetching();

		void onDataAvailable(List<T> items);

		void onDataAvailable(T item);

		void onDataError(IOException e);
	}

	public abstract void clearData();

	@UiThread void notifyAboutSuccess(IDataManagerListener<T> listener, T result) {
		if (listener != null) {
			listener.onDataAvailable(result);
		}
	}

	@UiThread void notifyAboutSuccess(IDataManagerListener<T> listener, List<T> result) {
		if (listener != null) {
			listener.onDataAvailable(result);
		}
	}

	@UiThread void notifyAboutStart(IDataManagerListener<T> listener) {
		if (listener != null) {
			listener.onDataStartFetching();
		}
	}

	@UiThread void notifyAboutFailed(IDataManagerListener<T> listener, IOException e) {
		if (listener != null) {
			listener.onDataError(e);
		}
	}

	public static class ActivityAwareListener<K> implements IDataManagerListener<K> {

		private WeakReference<Activity> activityWeakReference;
		private WeakReference<IDataManagerListener<K>> listenerWeakReference;

		public ActivityAwareListener(Activity activity, IDataManagerListener<K> listener) {
			activityWeakReference = new WeakReference<>(activity);
			listenerWeakReference = new WeakReference<>(listener);
		}

		@Override
		public final void onDataStartFetching() {
			if (isLive()) {
				listenerWeakReference.get().onDataStartFetching();
			}
		}

		@Override
		public final void onDataAvailable(List<K> items) {
			if (isLive()) {
				listenerWeakReference.get().onDataAvailable(items);
			}
		}

		@Override
		public final void onDataAvailable(K item) {
			if (isLive()) {
				listenerWeakReference.get().onDataAvailable(item);
			}
		}

		@Override
		public final void onDataError(IOException e) {
			if (isLive()) {
				listenerWeakReference.get().onDataError(e);
			}
		}

		private boolean isLive() {
			return !activityWeakReference.isEnqueued() &&
					activityWeakReference.get() != null &&
					!activityWeakReference.get().isFinishing() &&
					!listenerWeakReference.isEnqueued() &&
					listenerWeakReference.get() != null;
		}
	}
}
