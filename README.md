# ClearlyIP SMS Notification Driver for Hubitat

[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://opensource.org/licenses/MIT)

## Overview

The ClearlyIP SMS Notification Driver is a custom Hubitat device driver that allows you to send SMS notifications via your ClearlyIP account. This driver integrates seamlessly with your Hubitat environment, providing an easy-to-use method for sending SMS alerts directly from your automations.

## Features

- **Send SMS Notifications**: Trigger SMS alerts from Hubitat automations to any mobile number using ClearlyIP.
- **API Integration**: Connect your Hubitat system to ClearlyIP's SMS service using your ClearlyIP API credentials.
- **Customizable Messages**: Define the content of your SMS notifications within Hubitat's Rule Machine or any app that supports sending notifications.

## Installation

You can install the ClearlyIP SMS Notification Driver manually or by using the Hubitat Package Manager (HPM).

### Option 1: Install via Hubitat Package Manager (HPM)

1. Install the **Hubitat Package Manager** if you haven't already. Instructions can be found [here](https://hubitatpackagemanager.hubitatcommunity.com/installing.html).
2. Open **Hubitat Package Manager** from your Hubitat dashboard.
3. Select **Install** and search for "ClearlyIP SMS Notification Driver."
4. Follow the prompts to install the driver package.

### Option 2: Manual Installation

1. Copy the raw driver code from [`clearlyip-sms-notification-driver.groovy`](https://github.com/graftechnology/hubitat-clearlyip-sms-notification-driver/blob/main/clearlyip-sms-notification-driver.groovy).
2. In your Hubitat dashboard, navigate to **Drivers Code** and click on **New Driver**.
3. Paste the driver code and click **Save**.

### Step 2: Create a New Device
1. In your Hubitat dashboard, go to **Devices** and click **Add Device**.
2. Select **Virtual** as the device type.
3. Name your device and select `ClearlyIP SMS Notification Driver` from the drop-down list.
4. Save your device.

### Step 3: Configure the Driver
1. Once the device is created, go to the **Device Details** page for the new device.
2. Enter your **ClearlyIP API Key** and **ClearlyIP API Secret** into the respective fields.
3. Set the **Sender Phone Number** (this should be a number you own on your ClearlyIP account).
4. Optionally, set a **Default Recipient Number** if you want a specific number to receive all notifications.
5. Click **Save Preferences**.

### Step 4: Use the Driver in Automations
1. Open **Rule Machine** or any app that supports notifications.
2. Choose your ClearlyIP SMS device as the notification device.
3. Customize your SMS message.
4. Trigger your automation to send the message.

## Configuration Options

- **API Key**: ClearlyIP API Key required to authenticate requests.
- **API Secret**: ClearlyIP API Secret for authentication.
- **Sender Phone Number**: The phone number from your ClearlyIP account that will send the SMS.
- **Default Recipient Number** (optional): A default number to receive notifications if no recipient is specified in the automation.

## Example Rule Machine Usage

1. In **Rule Machine**, create a new rule or modify an existing rule.
2. Add an **Action** to send a notification.
3. Select the `ClearlyIP SMS Notification Driver` device.
4. Enter your custom message, or use Hubitat's built-in variables for dynamic messages (e.g., `%device% is now on`).

## Troubleshooting

- Ensure your ClearlyIP API Key and Secret are entered correctly.
- Double-check that the phone numbers you are using (sender and recipient) are registered in your ClearlyIP account.
- Make sure you have sufficient credits on your ClearlyIP account to send SMS messages.

## License

This driver is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

## Credits

This Hubitat driver package was developed by Graf Technology, LLC. If you have any issues or feature requests, please open an issue in the GitHub repository.
