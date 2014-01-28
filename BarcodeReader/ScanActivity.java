package barcode.reader;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;

import com.abhi.barcode.frag.libv2.BarcodeFragment;
import com.abhi.barcode.frag.libv2.IScanResultHandler;
import com.abhi.barcode.frag.libv2.ScanResult;

public class ScanActivity extends FragmentActivity implements IScanResultHandler {
		
	private BarcodeFragment fragment;

	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		
		fragment = (BarcodeFragment)getSupportFragmentManager().findFragmentById(R.id.Scanner);
		fragment.setScanResultHandler(this);
		fragment.setFrontCamera(true);

		LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("SCAN_ACTION"));
	}


	@Override
	protected void onPause() {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
		super.onPause();
	} 


	@Override
	public void scanResult(ScanResult result) {		
		Intent intent = new Intent("SCAN_RESULT");
		intent.putExtra("Code", result.getRawResult().getText());
		sendBroadcast(intent);
	}
}