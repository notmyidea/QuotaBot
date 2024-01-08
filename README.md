[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser)

# QuotaBot

QuotaBot is a Discord bot written in Java using JDK 8 and later, built with the JDA library. It fetches a famous quote from the quotable.io API and posts it in a specified channel every 24 hours.

## Table of Contents
- [Installation](#installation)
- [Daemonizing](#daemonizing)
- [Usage](#usage)
- [Contributing](#contributing)
- [Credits](#credits)
- [Support](#support)
- [Version](#version)

## Installation

1. Clone the repository:
   ```git clone https://github.com/notswedish/QuotaBot.git```

2. Compile the bot:
   ```javac -cp ".:path/to/JDA.jar:path/to/other/dependencies.jar" QuotaBot.java```

#### Or download the .jar in [the releases tab](https://github.com/notswedish/QuotaBot/releases)

3. Run the bot:
   ```java -jar QuotaBot.jar```
   It's compiled in Java 17, so you need to have Java 17 installed on your system to run it.
## Usage

Configure the bot by editing the .env file with these values
- TOKEN=token
- QOTD_CHANNEL_ID=channelid

## Daemonizing
You can create a systemd service unit file to manage your Java application as a service. Here's how to setup a systemd service for your application:
1. Create a new service file:
   ```sudo nano /etc/systemd/system/quotabot.service```

2. Add the following content
```
[Unit]
Description=QuotaBot
After=network.target

[Service]
User=root
WorkingDirectory=/home/<your_username>/QuotaBot
# may be /root/QuotaBot/
ExecStart=/usr/bin/java -jar /home/<your_username>/QuotaBot/QuotaBot-1.0-SNAPSHOT-jar-with-dependencies.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```
3. Now inform systemd about this new service:
   ```sudo systemctl daemon-reload```

4. Enable the service to start on boot:
   ```sudo systemctl enable quotabot ```

5. Now you can start your service:
   ```sudo systemctl start quotabot```

6. You can check the status of your service:
   ```sudo systemctl status quotabot```

7. To stop the service, you can use:
   ```sudo systemctl stop quotabot```

This configuration keeps your application running in the background and automatically starts it at system boot. It also restarts your application if it fails.
## Contributing

Feel free to contribute to the development of QuotaBot. If you have suggestions, bug reports, or want to add new features, open an issue or create a pull request.

## Credits

QuotaBot relies on the quotable.io API to fetch quotes from famous people. Thanks to quotable.io for providing this awesome service.

## Support

For any issues, questions, or support, contact the author:
- Discord: notmyidea



## Version

- **Current Version:** Working
- **Last Update:** 2024.08.01

## Made for the [PUREFACTIONS](https://discord.gg/purefactions) discord server

