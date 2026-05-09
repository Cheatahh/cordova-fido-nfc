# cordova-fido-nfc

Cordova plugin for FIDO over NFC on Android devices.

This project bridges native NFC functionality into Cordova applications, making it possible to run authentication flows without needing to insert a security keys. It is intended to act as a workaround for Android's current lack of NFC support for web-based FIDO calls. 

Note: This implementation currently only bridges the classic `getAssertion` call, intended to be used from `navigator.credentials.get`. Nothing fancy, I just needed it to function for work. Feel free to add more.

### Interfaces
```javascript
const FidoIntegration = {
    StatusCodes: {
        SUCCESS: 0x1000, // payload: result
        FAILURE: 0x2000, // payload: string (exception) | null
        FAILURE_INVALID_PIN: 0x2001, // payload: null
        FAILURE_DEVICE_UNSUPPORTED: 0x2002, // payload: null
        FAILURE_DEVICE_LOST: 0x2003, // payload: null
        FAILURE_NO_CREDENTIALS: 0x2004, // payload: null
        FAILURE_TOO_MANY_CREDENTIALS: 0x2005, // payload: [{name :string, id: string}]
        FAILURE_BLOCKED_PIN: 0x2006, // payload: null
        SIGNAL_PROGRESS_UPDATE: 0x3001, // payload: float
        SIGNAL_DEVICE_DISCOVERED: 0x3002 // payload: null
    },
    // FIDO2 getAssertion call
    getAssertion: function(
        clientData /*string (json)*/,
        rpId /*string (url)*/,
        userPin /*string | null*/,
        userId /*string (base64) | null*/,
        onMessage /*callback (code, payload) -> void*/
    ) {
        runExecCatching(onMessage, 'getAssertion', [clientData, rpId, userPin, userId]);
    },
    // redirect NFC reads to dev/null (f.e. in login screens when currently not scanning)
    nfcDevNull: function(onMessage /*callback (code, payload) -> void*/) {
        runExecCatching(onMessage, 'nfcDevNull', []);
    },
    // reset the plugin to its original state
    reset: function(onMessage /*callback (code, payload) -> void*/) {
        runExecCatching(onMessage, 'reset', []);
    }
};
```
