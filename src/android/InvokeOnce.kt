package com.fkmit.fido

import com.yubico.yubikit.core.YubiKeyDevice

class InvokeOnce(private val callback: (YubiKeyDevice) -> Unit): (YubiKeyDevice) -> Unit {
    private var isInvoked = false
    override fun invoke(device: YubiKeyDevice) {
        if(isInvoked) return
        isInvoked = true
        return callback(device)
    }
}