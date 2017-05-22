## Settlement daily report for the International Trade Executions
This application has been build as solutions to generate the trade reports that displays the settlement amounts in US dollar for the entities received from various clients.
Application loads instructions sent by various clients and then process them based on agreed foreign currency exchange for dollar.
The settlement amount received on weekend has been calculated based on respective countries calendar.


##Limitaions or Assumptions of the application programme
The application solution is not 100% and always there is scope for improvements.
So the inputs and outputs has been decided as an plain texts.
It is assumed that provided instruction format is agreed between clients and JPMC.
Application might throw exceptions if the format is changed and should be treated as normal.
The input formats are provided in this rad me file.
Assumed provided each entity type will have single trade type for a day either buy or sell for the day.
System.out.println has been used for demo prints of the report to reduce uses of any Logger API dependency.
Please open output reports in NOTEPAD++ for better visualise formats.
The input path for instructions is C:/jpmc/instructions.txt
The path for report is C:/jpmc/TradeReport.txt
The path for rank repots is C:/jpmc/Ranks.txt

## Input formats used, must be used below to proceed
please create file named instructions.txt inside c:/jpmc/instructions.txt, format must be below.

Entity Buy/Sell AgreedFx Currency InstructionDate SettlementDate Units Price per unit
foo B 0.11 SEK 01 Jan 2016 02 Jan 2016 900 900.25
bar s 0.12 INR 07 Jan 2016 06 Jan 2016 450 150.25

A sample file can be copied from the \settlementReport\src\test\resources\instructions.txt , please copy this and paste into c://jpmc/instructions.txt


## Main application class file name
  ReportApplication.java


## Requirements to build and execute the game:
OS 32/64 bit
JAVA 1.8(jdk1.8.0_40)
Maven 3.0.5
Junit 4.11

## To build the application:
 mvn clean install
 mvn verify test

## Test cases
Tests covers almost all expected functionality.
There are 37 test cases run when application is build, any changes in application can be easily caught by tests.

## To execute the application:
java -cp report-0.1.jar report.ReportApplication


##  sample output below
C:\jpmc>java -cp report-0.1.jar report.ReportApplication

Please open output reports in NOTEPAD++ for better visualise formats from output paths, defaults C://jpmc/


ACTUAL SETTLEMENT DATE		ENTITY		RANK 		USD INCOMING		USD OUTGOING		TOTAL ACCUMULATION PER SETTLEMENT DAY
04 Jan 2016 12:00 AM		JAI			1								89124.75				89124.75
04 Jan 2016 12:00 AM		CAT			4								800.40				89925.15
06 Jan 2016 12:00 AM		IAJ			1			8113.50									8113.50
06 Jan 2016 12:00 AM		TAT			2			5200.00									13313.50
19 May 2017 12:00 AM		MAT			3								20110.05				20110.05
22 May 2017 12:00 AM		BRK			2								24000.00				24000.00
22 May 2017 12:00 AM		MAT			3								7703.85				31703.85
Please open output reports in NOTEPAD++ for better visualise formats from output paths, defaults C://jpmc/

RANK 	ENTITY	RANKED_FOR	 TRADED_AMT_USD
1		IAJ		INOMING		8113.50
2		TAT		INOMING		5200.00
1		JAI		OUTGOING		89124.75
2		BRK		OUTGOING		24000.00
3		MAT		OUTGOING		20110.05
3		MAT		OUTGOING		7703.85
4		CAT		OUTGOING		800.40

Process finished with exit code 0
