package com.fkmit.fido

import org.apache.cordova.PluginResult

private const val FLAG_SUCCESS = 0x1000
private const val FLAG_ERROR = 0x2000
private const val FLAG_SIGNAL = 0x3000

enum class MessageCodes(val code: Int, val isTerminal: Boolean) {

    Success(FLAG_SUCCESS, true),

    Failure(FLAG_ERROR, true),

    FailureInvalidPin(FLAG_ERROR or 0x0001, true),

    FailureUnsupportedDevice(FLAG_ERROR or 0x0002, true),

    FailureDeviceLost(FLAG_ERROR or 0x0003, true),

    FailureNoCredentials(FLAG_ERROR or 0x0004, true),

    FailureTooManyCredentials(FLAG_ERROR or 0x0005, true),

    SignalProgressUpdate(FLAG_SIGNAL or 0x0001, false),

    SignalDeviceDiscovered(FLAG_SIGNAL or 0x0002, false)

}