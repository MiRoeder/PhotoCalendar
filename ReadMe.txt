Program to create Pages for a Personalized Photo Calendar

I like personalized photo calendars with my own pictures and my own events. They are also a good gift for parents and grandparents. You need 13 images: one for every month and one for the title page. This program supports you in two ways to generate the corresponding pages. 
* You define your own events such as birthdays, wedding anniversaries, regional holidays, school holidays, events of your sports club or similar once in the program. They are then automatically added to the calendar every year.
* Your pictures will be fitted into the calendar page. They can be rotated if necessary. Multiple pictures can be arranged automatically.
At the end you will send these 13 pages to a company like 
* WHITE WALL 
* ORWO 
* MYPOSTER 
* posterXXL 
* MediaMarkt / SATURN 
* Photobox 
* and many others … 
I already tried the first 5 companies myself and was satisfied. However, I cannot take responsibility for these companies. I do not have an agreement with any of them. 

Usage
I use the IDE Eclipse. To edit and run the photo calendar there, you have to open the following three project files: 
* DreiRoedersUtil/.project
* Painter/.project
* PhotoCalendar/.project
To generate suitable pages, you must adapt the program accordingly: 

Set Year and Start Month
The starting year and month will be set in the file PhotoCalendar/src/de/dreiroeders/fotokalender/FotoKalenderOpt.java: 
de.dreiroeders.fotokalender.FotoKalenderOpt. YEAR1 and in 
de.dreiroeders.fotokalender.FotoKalenderOpt. MONTH1. 
A calendar consists of 13 pages. Usually, the months January to December and a title page. However, I like to start calendars in July or August when the appointments of my Bundesliga club are published. Then I use the title page as a normal calendar page and thus have 13 months. Everyone can adapt this to suit themselves. 

Your Own Events
Your own events will be set in the file PhotoCalendar/src/de/dreiroeders/fotokalender/PhotoKalender.java in the method addDates(). I have so many dates that I distributed the dates across several subroutines. These subroutines are usually defined in the file PhotoCalendar/src/de/dreiroeders/fotokalender/FotoKalender.java. They can be found quickly using the usual commands in the development environment. It is best to follow these examples and then set your own dates. If you don't want private dates, you can of course remove all calls. 

Your Own Pictures
Your own images will be set in the file PhotoCalendar/src/de/dreiroeders/fotokalender/PhotoKalender.java in the method makeFamilyCal(FotoKalenderOpt trgOpt). It is best to use the examples as a guide. If one or more images are to be distributed evenly on a page, then Calendar.FEBRUARY is a good example. The images are distributed evenly on a page. Since the images usually do not fit exactly into the free fields, something is cut off on the left and right or top and bottom so that the images fit exactly on the page. 
To position the images precisely, Calendar.JUNE is a good example. 

Completion
Once a few pages are finished, you can look at the result. To do this, you start main(String[] args) in the file PhotoCalendar/src/de/dreiroeders/fotokalender/FotoKalenderOpt.java. Then you will find the generated image files in the Results folder. You will send these images to the selected company via the Internet so that they can print the final calendar. 
If you find that the dimensions of your own image files do not match the company's desired dimensions, adjust the static variable CalendarSheet.fWeight accordingly and rerun main(String[] args). The file PhotoCalendar/src/de/dreiroeders/fotokalender/FotoKalenderOpt.java is ideal for this. This variable defines the ratio of length to width of the calendar sheets generated. 

License Terms
I publish my code under GPLv3license. But I use some third-party code: 

JAMA : A Java Matrix Package by NIST
Authors
JAMA's initial design, as well as this reference implementation, was developed by 
from The MathWorks : Joe Hicklin, Cleve Moler and Peter Webb
and from NIST : Ronald F. Boisvert, Bruce Miller, Roldan Pozo and Karin Remington
 
Copyright Notice of Java Matrix Package
This software is a cooperative product of The MathWorks and the National Institute of Standards and Technology (NIST) which has been released to the public domain. Neither The MathWorks nor NIST assumes any responsibility whatsoever for its use by other parties, and makes no guarantees, expressed or implied, about its quality, reliability, or any other characteristic. 
