metadata {
    definition (name: "ClearlyIP Unity SMS API Notification Device", namespace: "graftechnology", author: "Graf Technology, LLC", description: "A device driver that sends SMS notifications via the ClearlyIP Unity SMS API") {
        capability "Notification"
    }
}

preferences {
    input name: "apiToken", type: "text", title: "API Credentials Keycode", description: "Your API Credentials Keycode located on location page", required: true
    input name: "fromDID", type: "text", title: "From DID (E.164)", description: "The sender phone number (e.g. +XXXXXXXXXXX)", required: true
    input name: "toDID", type: "text", title: "To DID (E.164)", description: "The recipient phone number (e.g. +XXXXXXXXXXX)", required: true
    input name: "apiUrl", type: "text", title: "API URL", description: "The URL to send the HTTP POST request", required: true, defaultValue: "https://unity.clearlyip.com/trunking/v2/sms/message"
    input name: "enableDebugLogging", type: "bool", title: "Enable Debug Logging?", defaultValue: true
    input name: "enableInfoLogging", type: "bool", title: "Enable Informational Logging?", defaultValue: true
}

def installed() {
    logInfo("Device Driver Installed")
    initialize()
}

def updated() {
    logInfo("Device Driver Updated")
    initialize()
    if (enableDebugLogging) {
        runIn(1800, disableDebugLogging)  // Automatically disable debug logging after 30 minutes
    }
}

def initialize() {
    if (enableDebugLogging) {
        log.debug "Debug Logging Enabled"
    }
    if (enableInfoLogging) {
        log.info "Informational Logging Enabled"
    }
}

def disableDebugLogging() {
    log.warn "Disabling Debug Logging After 30 Minutes"
    device.updateSetting("enableDebugLogging", [value: "false", type: "bool"])
}

def deviceNotification(message) {
    logDebug("Sending Notification: ${message}")

    // Collect necessary variables from preferences
    def fromDID = settings.fromDID
    def toDID = settings.toDID
    def apiToken = settings.apiToken
    def url = settings.apiUrl

    // Prepare the JSON body for the POST request
    def postBody = [
        from: "${fromDID}",
        to: ["${toDID}"],
        content: message
    ]

    logDebug("POST Body: ${postBody}")
    logDebug("API URL: ${url}")
    logDebug("X-Token: ${apiToken}")

    // Prepare the POST request parameters
    try {
        def params = [
            uri: url,
            headers: [
                "X-Token": apiToken
            ],
            body: postBody,
            timeout: 30  // Timeout after 30 seconds if the request takes too long
        ]

        // Execute the HTTP POST request
        httpPostJson(params) { resp ->
            if (resp.status == 200) {
                logDebug("HTTP Status 200 OK, Checking API Response")

                // Parse the JSON response
                def jsonResponse = resp.data
                def deliveryStatus = jsonResponse.status

                // Check if the delivery status is "delivered"
                if (deliveryStatus == "delivered") {
                    logInfo("Message Delivered Successfully: ${jsonResponse}")
                } else {
                    logError("Message Failed to Deliver: ${jsonResponse}")
                }
            } else {
                logError("Failed to Send Message: ${resp.status} - ${resp.data}")
            }
        }
    } catch (groovyx.net.http.HttpResponseException e) {
        logError("HTTP Error: ${e.statusCode}")
        logError("HTTP Error Data: ${e.response.data}")
    } catch (Exception e) {
        logError("Error While Sending Message: ${e.message}")
        log.error "Stacktrace: ", e
    }
}

def logDebug(msg) {
    if (enableDebugLogging) {
        log.debug msg
    }
}

def logInfo(msg) {
    if (enableInfoLogging) {
        log.info msg
    }
}

def logError(msg) {
    log.error msg
}
