# QuotaBot

QuotaBot is a Discord bot written in Java using JDK 8 and later, built with the JDA library. It fetches a quote of a famous person from the quotable.io API and posts it in a specified channel every 24 hours.

## Table of Contents
- [Installation](#installation)
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

## Contributing

Feel free to contribute to the development of QuotaBot. If you have suggestions, bug reports, or want to add new features, open an issue or create a pull request.

## Credits

QuotaBot relies on the quotable.io API to fetch quotes from famous people. Thanks to quotable.io for providing this awesome service.

## Support

For any issues, questions, or support, contact the author:
- Discord: notmyidea



## Version

- **Current Version:** Working
- **Last Update:** YYYY-MM-DD
-

## Made for the [PUREFACTIONS](https://discord.gg/purefactions) discord server

