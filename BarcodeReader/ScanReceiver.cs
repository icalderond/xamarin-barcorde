using Android.Content;
using Android.App;
using Android.Widget;
using System;
using System.Collections.Generic;
using System.Text;
using Android.Support.V4.Content;

namespace BarcodeReader
{
    [BroadcastReceiver]
    [IntentFilter(new[] { "SCAN_RESULT" })]
    public class ScanReceiver : BroadcastReceiver
    {
        public override void OnReceive(Context context, Intent intent)
        {
            string code = intent.GetStringExtra("Code");
            Toast.MakeText(context, "Scanned code: " + code, ToastLength.Long).Show();
            LocalBroadcastManager.GetInstance(context).SendBroadcast(new Intent("SCAN_ACTION"));
        }
    }
}
