package com.arkhipov.ayur.rbplants.base.cash

import android.os.AsyncTask

sealed class DoAfterTime(
    private var baseTime: Long,
    private var listener: GreetingTaskListener)
    : AsyncTask<Unit, Unit, Unit>()
{
    interface GreetingTaskListener
    {
        fun onGreetingGenerated(greetingText: String)
    }

    override fun doInBackground(vararg p0: Unit?)
    {
        Thread.sleep(baseTime)
    }

    override fun onPostExecute(result: Unit?)
    {
        listener.onGreetingGenerated("$baseTime $result")
    }
}