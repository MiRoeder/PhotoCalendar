package de.dreiroeders.fotokalender;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MyFussballBundesliga {

	public static void addMyFussballBundesliga(FotoKalender cal) {
		//SPIELPLAN BUNDESLIGA SAISON 2025/2026
		//27.06.2025 ©DFL Deutsche Fußball Liga e.V.
		//Sämtliche Rechte an dem Spielplan liegen bei DFL Deutsche Fußball Liga e.V.
		//Änderungen des Spielplans bleiben vorbehalten.
		//Alle weiteren Informationen zur Saison 2025/2026 unter bundesliga.de
		addTermin(cal, 22 , 8 , 22 , 8 , 2025 , " 1 1 FC Bayern München RB Leipzig"); //
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , "  1 2 Bayer 04 Leverkusen  TSG Hoffenheim ");
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , "  1 3 Eintracht Frankfurt SV Werder Bremen  ");
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , "  1 4 Sport-Club Freiburg  FC Augsburg ");
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , "  1 5 1.  FSV Mainz 05  1_FC Köln ");
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , " Borussia Mönchengladbach Hamburger SV  ");
		addTermin(cal, 23 , 8 , 23 , 8 , 2025 , " 1. FC Union Berlin VfB Stuttgart ");
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , " FC St. Pauli Borussia Dortmund ");
		addTermin(cal, 23 , 8 , 24 , 8 , 2025 , " 1_FC Heidenheim 1846 VfL Wolfsburg ");

		addTermin(cal,29,8,31,8, 2025, "Borussia Dortmund - 1. FC Union Berlin");
		addTermin(cal,29,8,31,8, 2025, "RB Leipzig - 1. FC Heidenheim 1846");
		addTermin(cal,29,8,31,8, 2025, "SV Werder Bremen - Bayer 04 Leverkusen");
		addTermin(cal,29,8,31,8, 2025, "VfB Stuttgart - Borussia Mönchengladbach");
		addTermin(cal,29,8,31,8, 2025, "VfL Wolfsburg - 1. FSV Mainz 05");
		addTermin(cal,29,8,31,8, 2025, "FC Augsburg - FC Bayern München");
		addTermin(cal,29,8,31,8, 2025, "TSG Hoffenheim - Eintracht Frankfurt");
		addTermin(cal,29,8,31,8, 2025, "1. FC Köln - Sport-Club Freiburg");
		addTermin(cal,29,8,31,8, 2025, "Hamburger SV - FC St. Pauli");

		addTermin(cal,12,9,14,9, 2025, "FC Bayern München - Hamburger SV");
		addTermin(cal,12,9,14,9, 2025, "Bayer 04 Leverkusen - Eintracht Frankfurt");
		addTermin(cal,12,9,14,9, 2025, "Sport-Club Freiburg - VfB Stuttgart");
		addTermin(cal,12,9,14,9, 2025, "1. FSV Mainz 05 - RB Leipzig");
		addTermin(cal,12,9,14,9, 2025, "Borussia Mönchengladbach - SV Werder Bremen");
		addTermin(cal,12,9,14,9, 2025, "VfL Wolfsburg - 1. FC Köln");
		addTermin(cal,13,9,13,9, 2025, "1. FC Union Berlin - TSG Hoffenheim");
		addTermin(cal,12,9,14,9, 2025, "FC St. Pauli - FC Augsburg");
		addTermin(cal,12,9,14,9, 2025, "1. FC Heidenheim 1846 - Borussia Dortmund");

		addTermin(cal,19,9,21,9, 2025, "Bayer 04 Leverkusen - Borussia Mönchengladbach");
		addTermin(cal,19,9,21,9, 2025, "Eintracht Frankfurt - 1. FC Union Berlin");
		addTermin(cal,19,9,21,9, 2025, "Borussia Dortmund - VfL Wolfsburg");
		addTermin(cal,19,9,21,9, 2025, "RB Leipzig - 1. FC Köln");
		addTermin(cal,19,9,21,9, 2025, "SV Werder Bremen - Sport-Club Freiburg");
		addTermin(cal,19,9,21,9, 2025, "VfB Stuttgart - FC St. Pauli");
		addTermin(cal,19,9,21,9, 2025, "FC Augsburg - 1. FSV Mainz 05");
		addTermin(cal,19,9,21,9, 2025, "TSG Hoffenheim - FC Bayern München");
		addTermin(cal,19,9,21,9, 2025, "Hamburger SV - 1. FC Heidenheim 1846");

		addTermin(cal,26,9,28,9, 2025, "FC Bayern München - SV Werder Bremen");
		addTermin(cal,26,9,28,9, 2025, "Sport-Club Freiburg - TSG Hoffenheim");
		addTermin(cal,26,9,28,9, 2025, "1. FSV Mainz 05 - Borussia Dortmund");
		addTermin(cal,26,9,28,9, 2025, "Borussia Mönchengladbach - Eintracht Frankfurt");
		addTermin(cal,26,9,28,9, 2025, "VfL Wolfsburg - RB Leipzig");
		addTermin(cal,28,9,28,9, 2025, "1. FC Union Berlin - Hamburger SV");
		addTermin(cal,26,9,28,9, 2025, "FC St. Pauli - Bayer 04 Leverkusen");
		addTermin(cal,26,9,28,9, 2025, "1. FC Heidenheim 1846 - FC Augsburg");
		addTermin(cal,26,9,28,9, 2025, "1. FC Köln - VfB Stuttgart");

		addTermin(cal,3,10,5,10, 2025, "Bayer 04 Leverkusen - 1. FC Union Berlin");
		addTermin(cal,3,10,5,10, 2025, "Eintracht Frankfurt - FC Bayern München");
		addTermin(cal,3,10,5,10, 2025, "Borussia Dortmund - RB Leipzig");
		addTermin(cal,3,10,5,10, 2025, "SV Werder Bremen - FC St. Pauli");
		addTermin(cal,3,10,5,10, 2025, "VfB Stuttgart - 1. FC Heidenheim 1846");
		addTermin(cal,3,10,5,10, 2025, "Borussia Mönchengladbach - Sport-Club Freiburg");
		addTermin(cal,3,10,5,10, 2025, "FC Augsburg - VfL Wolfsburg");
		addTermin(cal,3,10,5,10, 2025, "TSG Hoffenheim - 1. FC Köln");
		addTermin(cal,3,10,5,10, 2025, "Hamburger SV - 1. FSV Mainz 05");

		addTermin(cal,17,10,19,10, 2025, "FC Bayern München - Borussia Dortmund");
		addTermin(cal,17,10,19,10, 2025, "Sport-Club Freiburg - Eintracht Frankfurt");
		addTermin(cal,17,10,19,10, 2025, "1. FSV Mainz 05 - Bayer 04 Leverkusen");
		addTermin(cal,17,10,19,10, 2025, "RB Leipzig - Hamburger SV");
		addTermin(cal,17,10,19,10, 2025, "VfL Wolfsburg - VfB Stuttgart");
		addTermin(cal,17,10,19,10, 2025, "1. FC Union Berlin - Borussia Mönchengladbach");
		addTermin(cal,17,10,19,10, 2025, "FC St. Pauli - TSG Hoffenheim");
		addTermin(cal,17,10,19,10, 2025, "1. FC Heidenheim 1846 - SV Werder Bremen");
		addTermin(cal,17,10,19,10, 2025, "1. FC Köln - FC Augsburg");

		addTermin(cal,24,10,26,10, 2025, "Bayer 04 Leverkusen - Sport-Club Freiburg");
		addTermin(cal,24,10,26,10, 2025, "Eintracht Frankfurt - FC St. Pauli");
		addTermin(cal,24,10,26,10, 2025, "Borussia Dortmund - 1. FC Köln");
		addTermin(cal,24,10,26,10, 2025, "SV Werder Bremen - 1. FC Union Berlin");
		addTermin(cal,24,10,26,10, 2025, "VfB Stuttgart - 1. FSV Mainz 05");
		addTermin(cal,24,10,26,10, 2025, "Borussia Mönchengladbach - FC Bayern München");
		addTermin(cal,24,10,26,10, 2025, "FC Augsburg - RB Leipzig");
		addTermin(cal,24,10,26,10, 2025, "TSG Hoffenheim - 1. FC Heidenheim 1846");
		addTermin(cal,24,10,26,10, 2025, "Hamburger SV - VfL Wolfsburg");

		addTermin(cal,31,10,02,11, 2025, "FC Bayern München - Bayer 04 Leverkusen");
		addTermin(cal,31,10,02,11, 2025, "1. FSV Mainz 05 - SV Werder Bremen");
		addTermin(cal,31,10,02,11, 2025, "RB Leipzig - VfB Stuttgart");
		addTermin(cal,31,10,02,11, 2025, "VfL Wolfsburg - TSG Hoffenheim");
		addTermin(cal,31,10,02,11, 2025, "FC Augsburg - Borussia Dortmund");
		addTermin(cal,31,10,02,11, 2025, "1. FC Union Berlin - Sport-Club Freiburg");
		addTermin(cal,31,10,02,11, 2025, "FC St. Pauli - Borussia Mönchengladbach");
		addTermin(cal,31,10,02,11, 2025, "1. FC Heidenheim 1846 - Eintracht Frankfurt");
		addTermin(cal,31,10,02,11, 2025, "1. FC Köln - Hamburger SV");

		addTermin(cal,7,11,9,11, 2025, "Bayer 04 Leverkusen - 1. FC Heidenheim 1846");
		addTermin(cal,7,11,9,11, 2025, "Eintracht Frankfurt - 1. FSV Mainz 05");
		addTermin(cal,7,11,9,11, 2025, "Sport-Club Freiburg - FC St. Pauli");
		addTermin(cal,7,11,9,11, 2025, "SV Werder Bremen - VfL Wolfsburg");
		addTermin(cal,7,11,9,11, 2025, "VfB Stuttgart - FC Augsburg");
		addTermin(cal,7,11,9,11, 2025, "Borussia Mönchengladbach - 1. FC Köln");
		addTermin(cal,7,11,9,11, 2025, "1. FC Union Berlin - FC Bayern München");
		addTermin(cal,7,11,9,11, 2025, "TSG Hoffenheim - RB Leipzig");
		addTermin(cal,7,11,9,11, 2025, "Hamburger SV - Borussia Dortmund");

		addTermin(cal,21,11,23,11, 2025, "FC Bayern München - Sport-Club Freiburg");
		addTermin(cal,21,11,23,11, 2025, "Borussia Dortmund - VfB Stuttgart");
		addTermin(cal,21,11,23,11, 2025, "1. FSV Mainz 05 - TSG Hoffenheim");
		addTermin(cal,21,11,23,11, 2025, "RB Leipzig - SV Werder Bremen");
		addTermin(cal,21,11,23,11, 2025, "VfL Wolfsburg - Bayer 04 Leverkusen");
		addTermin(cal,21,11,23,11, 2025, "FC Augsburg - Hamburger SV");
		addTermin(cal,21,11,23,11, 2025, "FC St. Pauli - 1. FC Union Berlin");
		addTermin(cal,21,11,23,11, 2025, "1. FC Heidenheim 1846 - Borussia Mönchengladbach");
		addTermin(cal,21,11,23,11, 2025, "1. FC Köln - Eintracht Frankfurt");

		addTermin(cal,28,11,30,11, 2025, "FC Bayern München - FC St. Pauli");
		addTermin(cal,28,11,30,11, 2025, "Bayer 04 Leverkusen - Borussia Dortmund");
		addTermin(cal,28,11,30,11, 2025, "Eintracht Frankfurt - VfL Wolfsburg");
		addTermin(cal,28,11,30,11, 2025, "Sport-Club Freiburg - 1. FSV Mainz 05");
		addTermin(cal,28,11,30,11, 2025, "SV Werder Bremen - 1. FC Köln");
		addTermin(cal,28,11,30,11, 2025, "Borussia Mönchengladbach - RB Leipzig");
		addTermin(cal,28,11,30,11, 2025, "1. FC Union Berlin - 1. FC Heidenheim 1846");
		addTermin(cal,28,11,30,11, 2025, "TSG Hoffenheim - FC Augsburg");
		addTermin(cal,28,11,30,11, 2025, "Hamburger SV - VfB Stuttgart");

		addTermin(cal,5,12,7,12, 2025, "Borussia Dortmund - TSG Hoffenheim");
		addTermin(cal,5,12,7,12, 2025, "1. FSV Mainz 05 - Borussia Mönchengladbach");
		addTermin(cal,5,12,7,12, 2025, "RB Leipzig - Eintracht Frankfurt");
		addTermin(cal,5,12,7,12, 2025, "VfB Stuttgart - FC Bayern München");
		addTermin(cal,5,12,7,12, 2025, "VfL Wolfsburg - 1. FC Union Berlin");
		addTermin(cal,5,12,7,12, 2025, "FC Augsburg - Bayer 04 Leverkusen");
		addTermin(cal,5,12,7,12, 2025, "1. FC Heidenheim 1846 - Sport-Club Freiburg");
		addTermin(cal,5,12,7,12, 2025, "1. FC Köln - FC St. Pauli");
		addTermin(cal,5,12,7,12, 2025, "Hamburger SV - SV Werder Bremen");

		addTermin(cal,12,12,14,12, 2025, "FC Bayern München - 1. FSV Mainz 05");
		addTermin(cal,12,12,14,12, 2025, "Bayer 04 Leverkusen - 1. FC Köln");
		addTermin(cal,12,12,14,12, 2025, "Eintracht Frankfurt - FC Augsburg");
		addTermin(cal,12,12,14,12, 2025, "Sport-Club Freiburg - Borussia Dortmund");
		addTermin(cal,12,12,14,12, 2025, "SV Werder Bremen - VfB Stuttgart");
		addTermin(cal,12,12,14,12, 2025, "Borussia Mönchengladbach - VfL Wolfsburg");
		addTermin(cal,12,12,14,12, 2025, "1. FC Union Berlin - RB Leipzig");
		addTermin(cal,12,12,14,12, 2025, "FC St. Pauli - 1. FC Heidenheim 1846");
		addTermin(cal,12,12,14,12, 2025, "TSG Hoffenheim - Hamburger SV");

		addTermin(cal,19,12,21,12, 2025, "Borussia Dortmund - Borussia Mönchengladbach");
		addTermin(cal,19,12,21,12, 2025, "1. FSV Mainz 05 - FC St. Pauli");
		addTermin(cal,19,12,21,12, 2025, "RB Leipzig - Bayer 04 Leverkusen");
		addTermin(cal,19,12,21,12, 2025, "VfB Stuttgart - TSG Hoffenheim");
		addTermin(cal,19,12,21,12, 2025, "VfL Wolfsburg - Sport-Club Freiburg");
		addTermin(cal,19,12,21,12, 2025, "FC Augsburg - SV Werder Bremen");
		addTermin(cal,19,12,21,12, 2025, "1. FC Heidenheim 1846 - FC Bayern München");
		addTermin(cal,19,12,21,12, 2025, "1. FC Köln - 1. FC Union Berlin");
		addTermin(cal,19,12,21,12, 2025, "Hamburger SV - Eintracht Frankfurt");

		addTermin(cal,9,1,11,1, 2026, "FC Bayern München - VfL Wolfsburg");
		addTermin(cal,9,1,11,1, 2026, "Bayer 04 Leverkusen - VfB Stuttgart");
		addTermin(cal,9,1,11,1, 2026, "Eintracht Frankfurt - Borussia Dortmund");
		addTermin(cal,9,1,11,1, 2026, "Sport-Club Freiburg - Hamburger SV");
		addTermin(cal,9,1,11,1, 2026, "SV Werder Bremen - TSG Hoffenheim");
		addTermin(cal,9,1,11,1, 2026, "Borussia Mönchengladbach - FC Augsburg");
		addTermin(cal,9,1,11,1, 2026, "1. FC Union Berlin - 1. FSV Mainz 05");
		addTermin(cal,9,1,11,1, 2026, "FC St. Pauli - RB Leipzig");
		addTermin(cal,9,1,11,1, 2026, "1. FC Heidenheim 1846 - 1. FC Köln");

		addTermin(cal,13,1,15,1, 2026, "Borussia Dortmund - SV Werder Bremen");
		addTermin(cal,13,1,15,1, 2026, "1. FSV Mainz 05 - 1. FC Heidenheim 1846");
		addTermin(cal,13,1,15,1, 2026, "RB Leipzig - Sport-Club Freiburg");
		addTermin(cal,13,1,15,1, 2026, "VfB Stuttgart - Eintracht Frankfurt");
		addTermin(cal,13,1,15,1, 2026, "VfL Wolfsburg - FC St. Pauli");
		addTermin(cal,13,1,15,1, 2026, "FC Augsburg - 1. FC Union Berlin");
		addTermin(cal,13,1,15,1, 2026, "TSG Hoffenheim - Borussia Mönchengladbach");
		addTermin(cal,13,1,15,1, 2026, "1. FC Köln - FC Bayern München");
		addTermin(cal,13,1,15,1, 2026, "Hamburger SV - Bayer 04 Leverkusen");

		addTermin(cal,16,1,18,1, 2026, "Borussia Dortmund - FC St. Pauli");
		addTermin(cal,16,1,18,1, 2026, "RB Leipzig - FC Bayern München");
		addTermin(cal,16,1,18,1, 2026, "SV Werder Bremen - Eintracht Frankfurt");
		addTermin(cal,16,1,18,1, 2026, "VfB Stuttgart - 1. FC Union Berlin");
		addTermin(cal,16,1,18,1, 2026, "VfL Wolfsburg - 1. FC Heidenheim 1846");
		addTermin(cal,16,1,18,1, 2026, "FC Augsburg - Sport-Club Freiburg");
		addTermin(cal,16,1,18,1, 2026, "TSG Hoffenheim - Bayer 04 Leverkusen");
		addTermin(cal,16,1,18,1, 2026, "1. FC Köln - 1. FSV Mainz 05");
		addTermin(cal,16,1,18,1, 2026, "Hamburger SV - Borussia Mönchengladbach");

		addTermin(cal,23,1,25,1, 2026, "1. FC Union Berlin - Borussia Dortmund");
		addTermin(cal,23,1,25,1, 2026, "1. FC Heidenheim 1846 - RB Leipzig");
		addTermin(cal,23,1,25,1, 2026, "Bayer 04 Leverkusen - SV Werder Bremen");
		addTermin(cal,23,1,25,1, 2026, "Borussia Mönchengladbach - VfB Stuttgart");
		addTermin(cal,23,1,25,1, 2026, "1. FSV Mainz 05 - VfL Wolfsburg");
		addTermin(cal,23,1,25,1, 2026, "FC Bayern München - FC Augsburg");
		addTermin(cal,23,1,25,1, 2026, "Eintracht Frankfurt - TSG Hoffenheim");
		addTermin(cal,23,1,25,1, 2026, "Sport-Club Freiburg - 1. FC Köln");
		addTermin(cal,23,1,25,1, 2026, "FC St. Pauli - Hamburger SV");

		addTermin(cal,30,1,1,2, 2026, "Hamburger SV - FC Bayern München");
		addTermin(cal,30,1,1,2, 2026, "Eintracht Frankfurt - Bayer 04 Leverkusen");
		addTermin(cal,30,1,1,2, 2026, "VfB Stuttgart - Sport-Club Freiburg");
		addTermin(cal,30,1,1,2, 2026, "RB Leipzig - 1. FSV Mainz 05");
		addTermin(cal,30,1,1,2, 2026, "SV Werder Bremen - Borussia Mönchengladbach");
		addTermin(cal,30,1,1,2, 2026, "1. FC Köln - VfL Wolfsburg");
		addTermin(cal,30,1,1,2, 2026, "TSG Hoffenheim - 1. FC Union Berlin");
		addTermin(cal,30,1,1,2, 2026, "FC Augsburg - FC St. Pauli");
		addTermin(cal,30,1,1,2, 2026, "Borussia Dortmund - 1. FC Heidenheim 1846");

		addTermin(cal,6,2,8,2, 2026, "Borussia Mönchengladbach - Bayer 04 Leverkusen");
		addTermin(cal,6,2,8,2, 2026, "1. FC Union Berlin - Eintracht Frankfurt");
		addTermin(cal,6,2,8,2, 2026, "VfL Wolfsburg - Borussia Dortmund");
		addTermin(cal,6,2,8,2, 2026, "1. FC Köln - RB Leipzig");
		addTermin(cal,6,2,8,2, 2026, "Sport-Club Freiburg - SV Werder Bremen");
		addTermin(cal,6,2,8,2, 2026, "FC St. Pauli - VfB Stuttgart");
		addTermin(cal,6,2,8,2, 2026, "1. FSV Mainz 05 - FC Augsburg");
		addTermin(cal,6,2,8,2, 2026, "FC Bayern München - TSG Hoffenheim");
		addTermin(cal,6,2,8,2, 2026, "1. FC Heidenheim 1846 - Hamburger SV");

		addTermin(cal,13,2,15,2, 2026, "SV Werder Bremen - FC Bayern München");
		addTermin(cal,13,2,15,2, 2026, "TSG Hoffenheim - Sport-Club Freiburg");
		addTermin(cal,13,2,15,2, 2026, "Borussia Dortmund - 1. FSV Mainz 05");
		addTermin(cal,13,2,15,2, 2026, "Eintracht Frankfurt - Borussia Mönchengladbach");
		addTermin(cal,13,2,15,2, 2026, "RB Leipzig - VfL Wolfsburg");
		addTermin(cal,13,2,15,2, 2026, "Hamburger SV - 1. FC Union Berlin");
		addTermin(cal,13,2,15,2, 2026, "Bayer 04 Leverkusen - FC St. Pauli");
		addTermin(cal,13,2,15,2, 2026, "FC Augsburg - 1. FC Heidenheim 1846");
		addTermin(cal,13,2,15,2, 2026, "VfB Stuttgart - 1. FC Köln");

		addTermin(cal,20,2,22,2, 2026, "1. FC Union Berlin - Bayer 04 Leverkusen");
		addTermin(cal,20,2,22,2, 2026, "FC Bayern München - Eintracht Frankfurt");
		addTermin(cal,20,2,22,2, 2026, "RB Leipzig - Borussia Dortmund");
		addTermin(cal,20,2,22,2, 2026, "FC St. Pauli - SV Werder Bremen");
		addTermin(cal,20,2,22,2, 2026, "1. FC Heidenheim 1846 - VfB Stuttgart");
		addTermin(cal,20,2,22,2, 2026, "Sport-Club Freiburg - Borussia Mönchengladbach");
		addTermin(cal,20,2,22,2, 2026, "VfL Wolfsburg - FC Augsburg");
		addTermin(cal,20,2,22,2, 2026, "1. FC Köln - TSG Hoffenheim");
		addTermin(cal,20,2,22,2, 2026, "1. FSV Mainz 05 - Hamburger SV");

		addTermin(cal,27,2,1,3, 2026, "Borussia Dortmund - FC Bayern München");
		addTermin(cal,27,2,1,3, 2026, "Eintracht Frankfurt - Sport-Club Freiburg");
		addTermin(cal,27,2,1,3, 2026, "Bayer 04 Leverkusen - 1. FSV Mainz 05");
		addTermin(cal,27,2,1,3, 2026, "Hamburger SV - RB Leipzig");
		addTermin(cal,27,2,1,3, 2026, "VfB Stuttgart - VfL Wolfsburg");
		addTermin(cal,27,2,1,3, 2026, "Borussia Mönchengladbach - 1. FC Union Berlin");
		addTermin(cal,27,2,1,3, 2026, "TSG Hoffenheim - FC St. Pauli");
		addTermin(cal,27,2,1,3, 2026, "SV Werder Bremen - 1. FC Heidenheim 1846");
		addTermin(cal,27,2,1,3, 2026, "FC Augsburg - 1. FC Köln");

		addTermin(cal,6,3,8,3, 2026, "Sport-Club Freiburg - Bayer 04 Leverkusen");
		addTermin(cal,6,3,8,3, 2026, "FC St. Pauli - Eintracht Frankfurt");
		addTermin(cal,6,3,8,3, 2026, "1. FC Köln - Borussia Dortmund");
		addTermin(cal,6,3,8,3, 2026, "1. FC Union Berlin - SV Werder Bremen");
		addTermin(cal,6,3,8,3, 2026, "1. FSV Mainz 05 - VfB Stuttgart");
		addTermin(cal,6,3,8,3, 2026, "FC Bayern München - Borussia Mönchengladbach");
		addTermin(cal,6,3,8,3, 2026, "RB Leipzig - FC Augsburg");
		addTermin(cal,6,3,8,3, 2026, "1. FC Heidenheim 1846 - TSG Hoffenheim");
		addTermin(cal,6,3,8,3, 2026, "VfL Wolfsburg - Hamburger SV");

		addTermin(cal,13,3,15,3, 2026, "Bayer 04 Leverkusen - FC Bayern München");
		addTermin(cal,13,3,15,3, 2026, "SV Werder Bremen - 1. FSV Mainz 05");
		addTermin(cal,13,3,15,3, 2026, "VfB Stuttgart - RB Leipzig");
		addTermin(cal,13,3,15,3, 2026, "TSG Hoffenheim - VfL Wolfsburg");
		addTermin(cal,13,3,15,3, 2026, "Borussia Dortmund - FC Augsburg");
		addTermin(cal,13,3,15,3, 2026, "Sport-Club Freiburg - 1. FC Union Berlin");
		addTermin(cal,13,3,15,3, 2026, "Borussia Mönchengladbach - FC St. Pauli");
		addTermin(cal,13,3,15,3, 2026, "Eintracht Frankfurt - 1. FC Heidenheim 1846");
		addTermin(cal,13,3,15,3, 2026, "Hamburger SV - 1. FC Köln");

		addTermin(cal,20,3,22,3, 2026, "1. FC Heidenheim 1846 - Bayer 04 Leverkusen");
		addTermin(cal,20,3,22,3, 2026, "1. FSV Mainz 05 - Eintracht Frankfurt");
		addTermin(cal,20,3,22,3, 2026, "FC St. Pauli - Sport-Club Freiburg");
		addTermin(cal,20,3,22,3, 2026, "VfL Wolfsburg - SV Werder Bremen");
		addTermin(cal,20,3,22,3, 2026, "FC Augsburg - VfB Stuttgart");
		addTermin(cal,20,3,22,3, 2026, "1. FC Köln - Borussia Mönchengladbach");
		addTermin(cal,20,3,22,3, 2026, "FC Bayern München - 1. FC Union Berlin");
		addTermin(cal,20,3,22,3, 2026, "RB Leipzig - TSG Hoffenheim");
		addTermin(cal,20,3,22,3, 2026, "Borussia Dortmund - Hamburger SV");

		addTermin(cal,4,4,5,4, 2026, "Sport-Club Freiburg - FC Bayern München");
		addTermin(cal,4,4,5,4, 2026, "VfB Stuttgart - Borussia Dortmund");
		addTermin(cal,4,4,5,4, 2026, "TSG Hoffenheim - 1. FSV Mainz 05");
		addTermin(cal,4,4,5,4, 2026, "SV Werder Bremen - RB Leipzig");
		addTermin(cal,4,4,5,4, 2026, "Bayer 04 Leverkusen - VfL Wolfsburg");
		addTermin(cal,4,4,5,4, 2026, "Hamburger SV - FC Augsburg");
		addTermin(cal,4,4,5,4, 2026, "1. FC Union Berlin - FC St. Pauli");
		addTermin(cal,4,4,5,4, 2026, "Borussia Mönchengladbach - 1. FC Heidenheim 1846");
		addTermin(cal,4,4,5,4, 2026, "Eintracht Frankfurt - 1. FC Köln");

		addTermin(cal,10,4,12,4, 2026, "FC St. Pauli - FC Bayern München");
		addTermin(cal,10,4,12,4, 2026, "Borussia Dortmund - Bayer 04 Leverkusen");
		addTermin(cal,10,4,12,4, 2026, "VfL Wolfsburg - Eintracht Frankfurt");
		addTermin(cal,10,4,12,4, 2026, "1. FSV Mainz 05 - Sport-Club Freiburg");
		addTermin(cal,10,4,12,4, 2026, "1. FC Köln - SV Werder Bremen");
		addTermin(cal,10,4,12,4, 2026, "RB Leipzig - Borussia Mönchengladbach");
		addTermin(cal,10,4,12,4, 2026, "1. FC Heidenheim 1846 - 1. FC Union Berlin");
		addTermin(cal,10,4,12,4, 2026, "FC Augsburg - TSG Hoffenheim");
		addTermin(cal,10,4,12,4, 2026, "VfB Stuttgart - Hamburger SV");

		addTermin(cal,17,4,19,4, 2026, "TSG Hoffenheim - Borussia Dortmund");
		addTermin(cal,17,4,19,4, 2026, "Borussia Mönchengladbach - 1. FSV Mainz 05");
		addTermin(cal,17,4,19,4, 2026, "Eintracht Frankfurt - RB Leipzig");
		addTermin(cal,17,4,19,4, 2026, "FC Bayern München - VfB Stuttgart");
		addTermin(cal,17,4,19,4, 2026, "1. FC Union Berlin - VfL Wolfsburg");
		addTermin(cal,17,4,19,4, 2026, "Bayer 04 Leverkusen - FC Augsburg");
		addTermin(cal,17,4,19,4, 2026, "Sport-Club Freiburg - 1. FC Heidenheim 1846");
		addTermin(cal,17,4,19,4, 2026, "FC St. Pauli - 1. FC Köln");
		addTermin(cal,17,4,19,4, 2026, "SV Werder Bremen - Hamburger SV");

		addTermin(cal,24,4,26,4, 2026, "1. FSV Mainz 05 - FC Bayern München");
		addTermin(cal,24,4,26,4, 2026, "1. FC Köln - Bayer 04 Leverkusen");
		addTermin(cal,24,4,26,4, 2026, "FC Augsburg - Eintracht Frankfurt");
		addTermin(cal,24,4,26,4, 2026, "Borussia Dortmund - Sport-Club Freiburg");
		addTermin(cal,24,4,26,4, 2026, "VfB Stuttgart - SV Werder Bremen");
		addTermin(cal,24,4,26,4, 2026, "VfL Wolfsburg - Borussia Mönchengladbach");
		addTermin(cal,24,4,26,4, 2026, "RB Leipzig - 1. FC Union Berlin");
		addTermin(cal,24,4,26,4, 2026, "1. FC Heidenheim 1846 - FC St. Pauli");
		addTermin(cal,24,4,26,4, 2026, "Hamburger SV - TSG Hoffenheim");

		addTermin(cal,2,5,3,5, 2026, "Borussia Mönchengladbach - Borussia Dortmund");
		addTermin(cal,2,5,3,5, 2026, "FC St. Pauli - 1. FSV Mainz 05");
		addTermin(cal,2,5,3,5, 2026, "Bayer 04 Leverkusen - RB Leipzig");
		addTermin(cal,2,5,3,5, 2026, "TSG Hoffenheim - VfB Stuttgart");
		addTermin(cal,2,5,3,5, 2026, "Sport-Club Freiburg - VfL Wolfsburg");
		addTermin(cal,2,5,3,5, 2026, "SV Werder Bremen - FC Augsburg");
		addTermin(cal,2,5,3,5, 2026, "FC Bayern München - 1. FC Heidenheim 1846");
		addTermin(cal,2,5,3,5, 2026, "1. FC Union Berlin - 1. FC Köln");
		addTermin(cal,2,5,3,5, 2026, "Eintracht Frankfurt - Hamburger SV");

		addTermin(cal,8,5,10,5, 2026, "VfL Wolfsburg - FC Bayern München");
		addTermin(cal,8,5,10,5, 2026, "VfB Stuttgart - Bayer 04 Leverkusen");
		addTermin(cal,8,5,10,5, 2026, "Borussia Dortmund - Eintracht Frankfurt");
		addTermin(cal,8,5,10,5, 2026, "Hamburger SV - Sport-Club Freiburg");
		addTermin(cal,8,5,10,5, 2026, "TSG Hoffenheim - SV Werder Bremen");
		addTermin(cal,8,5,10,5, 2026, "FC Augsburg - Borussia Mönchengladbach");
		addTermin(cal,8,5,10,5, 2026, "1. FSV Mainz 05 - 1. FC Union Berlin");
		addTermin(cal,8,5,10,5, 2026, "RB Leipzig - FC St. Pauli");
		addTermin(cal,8,5,10,5, 2026, "1. FC Köln - 1. FC Heidenheim 1846");

		addTermin(cal,16,5,16,5, 2026, "SV Werder Bremen - Borussia Dortmund");
		addTermin(cal,16,5,16,5, 2026, "1. FC Heidenheim 1846 - 1. FSV Mainz 05");
		addTermin(cal,16,5,16,5, 2026, "Sport-Club Freiburg - RB Leipzig");
		addTermin(cal,16,5,16,5, 2026, "Eintracht Frankfurt - VfB Stuttgart");
		addTermin(cal,16,5,16,5, 2026, "FC St. Pauli - VfL Wolfsburg");
		addTermin(cal,16,5,16,5, 2026, "1. FC Union Berlin - FC Augsburg");
		addTermin(cal,16,5,16,5, 2026, "Borussia Mönchengladbach - TSG Hoffenheim");
		addTermin(cal,16,5,16,5, 2026, "FC Bayern München - 1. FC Köln");
		addTermin(cal,16,5,16,5, 2026, "Bayer 04 Leverkusen - Hamburger SV");
		
		if (cal.THIS_YEAR == 2024) {
			//BUNDESLIGA START am Freitag 23. August 2024. Im Jahr 2025 dann wohl 52 Wochen später
			PersonalDate sp;
			sp = PersonalDate.create2BitmapsBackground(new File("res/FC Bayern München.jpg"), " ... ", new File("res/RB Leipzig.png"), 0.7f, 0f, 23, 8, 2025);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(new File("res/Bayer Leverkusen.png"), " ... ", new File("res/Borussia Mönchengladbach.jpg"), 0.7f, 0f, 22, 8, 2025);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(new File("res/BVB 09 Dortmund.jpg"), "? ... ", new File("res/UnionBerlin.png"), 0.7f, 0.5f, 23, 8, 2025);
			cal.mDates.addCalEvent(sp);
		}
		if (cal.THIS_YEAR == 2023) {
			File unionIco = new File("res/UnionBerlin.png");
			PersonalDate sp;
			sp = PersonalDate.create2BitmapsBackground(unionIco, " : ", new File("res/Sp. Braga.png"), 2f, 0f, 3, 10, 2023);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(unionIco, " : ", new File("res/SSC Neapel.png"), 2f, 0f, 24, 10, 2023);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(unionIco, " : ", new File("res/Real Madrid.png"), 2f, 0f, 12, 12, 2023);
			cal.mDates.addCalEvent(sp);
		}
	}
	

	enum HinRueckRunde {
		HinRunde,
		RueckRunde
	}


	public static void addTermin(
		FotoKalender cal,
		int nRuekDays,
		int nRuekLastDay,
		int nRuekMonth, /* January = 1, ... December = 12 */
		int nRuekYear,
		int nHinDays,
		int nHinLastDay,
		int nHinMonth, /* January = 1, ... December = 12 */
		int nHinYear,
		String str
	) {
		if (nHinYear == cal.THIS_YEAR) {
			addTermin(cal, HinRueckRunde.HinRunde, nHinDays, new GregorianCalendar(nHinYear, nHinMonth-1, nHinLastDay), str);
		}
		if (nRuekYear == cal.THIS_YEAR) {
			addTermin(cal, HinRueckRunde.RueckRunde,nRuekDays, new GregorianCalendar(nRuekYear, nRuekMonth-1, nRuekLastDay), str);
		}
	}
	

	public static void addTermin(
			FotoKalender cal,
			int n1stDay,
			int n1stMon, /* January = 1, ... December = 12 */
			int nLastDay,
			int nLastMon, /* January = 1, ... December = 12 */
			int nYear,
			String str
		) {
			GregorianCalendar cal1 = new GregorianCalendar(nYear, n1stMon-1, n1stDay);
			GregorianCalendar cal2 = new GregorianCalendar(nYear, nLastMon-1,nLastDay);
			int nDaysPlusMinus = (int)((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / 86400000);
			addTermin(cal, HinRueckRunde.HinRunde, nDaysPlusMinus, cal2, str);
		}
		

	public static void addTermin(
		FotoKalender cal,
		HinRueckRunde hinRueck,
		int nDays,
		GregorianCalendar lastDay,
		String str
	) {
		GregorianCalendar firstDay;
		if (nDays == 0) {
			firstDay = lastDay;
		} else {
			firstDay = (GregorianCalendar)lastDay.clone();
			firstDay.add(Calendar.DAY_OF_MONTH, -nDays);
		}
		System.out.println("Am Bundesligaspieltag "
		                   + firstDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) +" "+ firstDay.get(Calendar.DAY_OF_MONTH) +"."+ (firstDay.get(Calendar.MONTH)+1)
						   + " ... " 
		                   + lastDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())  +" "+  lastDay.get(Calendar.DAY_OF_MONTH) +"."+ ( lastDay.get(Calendar.MONTH)+1) );
		String strNames[] = str.split(" ");
		Club clubs[] = new Club[strNames.length];
		int nClubs = 0;
		for (int iB = 0; iB < strNames.length; ++iB) {
			 Club cb = findClub(strNames[iB]);
			 clubs[iB] = cb;
			 if (cb != null) {
				 ++nClubs;
			 } else {
				 if (strNames[iB].length() > 3) {
					 System.out.println("Ignoriere Name: " + strNames[iB]);
				 }
			 }
		}
		System.out.println("Spielen "+ nClubs +" Vereine");
		Club selHin = null;
		Club selRuek = null;
		float fValBestmatch = 0.95f;
		for (int iB = 0; iB < strNames.length-1; ++iB) {
			if (clubs[iB] != null) {
				Club rueckClub = null;
				int jB;
				for (jB = iB+1; jB < strNames.length && rueckClub == null; ++jB) {
					rueckClub = clubs[jB];
				}
				if (rueckClub != null) {
					float fCurMatch = hinRueck == HinRueckRunde.HinRunde ? clubs[iB].mfImpHeim * rueckClub.mfImpAusw : clubs[iB].mfImpAusw * rueckClub.mfImpHeim;
					if (fCurMatch > fValBestmatch) {
						selHin = hinRueck == HinRueckRunde.HinRunde ? clubs[iB] : rueckClub;
						selRuek = hinRueck == HinRueckRunde.HinRunde ? rueckClub : clubs[iB];
						fValBestmatch = fCurMatch;
					}
					iB = jB+1;
				}
			}
		}
		if (selHin != null && selRuek != null) {
			System.out.println("Ausgewähltes Spiel: "+ selHin.mStrIcoName +" - "+ selRuek.mStrIcoName);
			addTermin(cal, nDays, firstDay, lastDay, selHin.mStrIcoName, selRuek.mStrIcoName, fValBestmatch);
		}
	}
		
	
	public static void addTermin(
			FotoKalender cal,
			int nDays,
			GregorianCalendar firstDay,
			GregorianCalendar lastDay,
			String strHeimIcoName,
			String strAuswIcoName,
			float factor
		) {
		int nWeekDay = lastDay.get(Calendar.DAY_OF_WEEK);
		float deltaY;
		if (nDays == 0) {
			deltaY = 0f;
		} else {
			if      (nDays == 1 && nWeekDay == Calendar.WEDNESDAY) deltaY = -4f/9f;  // 4 Spiele am Dienstag
			else if (nDays == 1 && nWeekDay == Calendar.SUNDAY)    deltaY = -6f/8f;  // 6 Spiele am Samstag
			else if (nDays == 2 && nWeekDay == Calendar.SUNDAY)    deltaY = -(2*1+6)/9f; // 1 Spiel am Freitag + 6 Spiele am Samstag
			else if (nDays == 2 && nWeekDay == Calendar.MONDAY)    deltaY = -(2*6+2)/9f; // 6 Spiele am Samstag + 2 Spiele am Sonntag
			else if (nDays == 3 && nWeekDay == Calendar.MONDAY)    deltaY = -(3*1+2*5+2)/9f; // 1 Spiel am Freitag + 5 Spiele am Samstag + 2 Spiele am Sonntag
			else												   deltaY = -nDays/2f;
		}
		File filHeim = new File("res/"+strHeimIcoName);
		File filAusw = new File("res/"+strAuswIcoName);
		int iDeltaY = Math.round(deltaY);
		if (firstDay.get(Calendar.MONTH) == lastDay.get(Calendar.MONTH)) {
			GregorianCalendar cenDay = lastDay;
			if (iDeltaY != 0) {
				deltaY -= iDeltaY;
				cenDay = (GregorianCalendar)(cenDay.clone());
				cenDay.add(GregorianCalendar.DAY_OF_MONTH, iDeltaY);
			}
			ArrayList<PersonalDate> alreadyEvents = cal.mDates.getCalEvents(cenDay);
			deltaY += alreadyEvents.size() * Math.min(factor, 0.3f);
			PersonalDate sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, factor, deltaY, cenDay);
			cal.mDates.addCalEvent(sp);
			for (int iD = 0; iD <= nDays; ++iD) {
				cal.mDates.addCalEvent01(PersonalDate.createDate("{0,date,EE} ", sp.getDate(), -iD-iDeltaY, (byte)0));
			}
		} else {
			int n1 = 0;
			int n2 = 0;
			final int lDayIMth = lastDay.get(Calendar.DAY_OF_MONTH);
			for (int iD = 0; iD <= nDays; ++iD) {
				if (iD < lDayIMth) {
					n2 += getNSpielePerDay(nDays, nWeekDay-iD);
				} else {
					n1 += getNSpielePerDay(nDays, nWeekDay-iD);
				}
				assert(n1 > 0 && n2 > 0);
			}
			final int fDayIMth = firstDay.get(Calendar.DAY_OF_MONTH);
			float deltaY1 = Math.min(fDayIMth + nDays + deltaY, 31.25f) - fDayIMth;
			float deltaY2 = Math.max(deltaY, -lDayIMth+0.75f);
			float fac1 = factor * (float)Math.sqrt((float)n1/(n1+n2));
			float fac2 = factor * (float)Math.sqrt((float)n2/(n1+n2));
			PersonalDate sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, fac1, deltaY1, firstDay);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, fac2, deltaY2, lastDay);
			cal.mDates.addCalEvent(sp);
			for (int iD = 1; iD < nDays; ++iD) {
				cal.mDates.addCalEvent01(PersonalDate.createDate("{0,date,EE} ", sp.getDate(), -iD, (byte)0));
			}
		}
	}

	
	public static int getNSpielePerDay(int nDays, int nWeekDay1) {
		int nWeekDay = nWeekDay1;
		if (nWeekDay < 1) {
			nWeekDay += 7;
		}
		if (nDays == 1) {
			switch (nWeekDay) {
			    case Calendar.TUESDAY:  return 4;
				case Calendar.WEDNESDAY:return 5;
				case Calendar.SATURDAY: return 6;
				case Calendar.SUNDAY:   return 2;
			}
		}
		if (nDays == 2) {
			switch (nWeekDay) {
				case Calendar.FRIDAY:   return 1;
				case Calendar.SATURDAY: return 6;
				case Calendar.SUNDAY:   return 2;
			    case Calendar.MONDAY:   return 1;
			}
		}
		if (nDays == 3) {
			switch (nWeekDay) {
				case Calendar.FRIDAY:   return 1;
				case Calendar.SATURDAY: return 5;
				case Calendar.SUNDAY:   return 2;
				case Calendar.MONDAY:   return 1;
			}
		}
		return (9+nDays)/(nDays+1);
	}
	
	
	private static class Club {
		
		public final String mStrIcoName; // The name of the icon.
				// It must contain the name of the club as used by the list 
				// in  addMyFussballBundesliga(FotoKalender cal)
		public final float mfImpHeim;  // The importance of the club for you when it plays at home
		public final float mfImpAusw;  // The importance of the club for you when it plays away
		
		/**
		 * 
		 * @param strIcoName The name of the icon.
		 * 			It must contain the name of the club as used by the list in  addMyFussballBundesliga(FotoKalender cal)
		 * @param fImpHeim   The importance of the club for you when it plays at home
		 * @param fImpAusw   The importance of the club for you when it plays away
		 */
		public Club(String strIcoName, float fImpHeim, float fImpAusw) {
			super();
			this.mStrIcoName = strIcoName;
			this.mfImpHeim = fImpHeim;
			this.mfImpAusw = fImpAusw;
		}
		
		public boolean isClub(String strName) {
			boolean bResult = strName != null && strName.length() > 3;
			if (bResult) {
				int iRes = mStrIcoName.indexOf(strName);
				bResult = iRes >= 0 && iRes+strName.length()+8 >= mStrIcoName.length();
			}
			return bResult; 
		}
	}
		
	
	private static final Club[] sClubs = {
			new Club("1.FC_Nürnberg.png", 0f, 1f),
			new Club("Arminia_Bielefeld.png", 0f, 1f),
			new Club("Bayer Leverkusen.png", 0f, 1f),
			new Club("Borussia Mönchengladbach.jpg", 0f, 1f),
			new Club("BVB 09 Dortmund.jpg", 0f, 1f),
			new Club("Dynamo Dresden.png", .0f, 1f),
			new Club("Eintr Frankfurt.jpg", 0f, 1f),
			new Club("Eintracht Braunschweig.png", 0f, 1f),
			new Club("FC Augsburg.jpg", 0f, 1f),
			new Club("FC Bayern München.jpg", 0f, 1f),
			new Club("FC Heidenheim 1846.png", 0f, 1f),
			new Club("FC Ingolstadt 04.jpg", 0f, 1f),
			new Club("FC Köln.jpg", 0.8f, 1f),
			new Club("FC Kaiserslautern.png", 0f, 1f),
			new Club("FC Magdeburg.png", .0f, 1f),
			new Club("FC St. Pauli.png", .0f, 1f),
			new Club("Fortuna_Düsseldorf.svg.png", 0f, 1f),
			new Club("FSV Mainz 05.jpg", 0f, 1f),
			new Club("Hamburger SV.jpg", .0f, 1f),
			new Club("Hannover 96.jpg", .94f, 1f),
			new Club("Hansa Rostock.png", .0f, 1f),
			new Club("Hertha BSC.jpg", 2f, 1f),
			new Club("Holstein Kiel.png", 0f, 1f),
			new Club("Karlsruher SC.png", .93f, 1f),
			new Club("RB Leipzig.png", 0f, 1f),
			new Club("SC Freiburg.jpg", 0f, 1f),
			new Club("SC Paderborn.png", 0f, 1f),
			new Club("Schalke 04.jpg", 0.91f, 1f),
			new Club("SpVgg_Greuther_Fürth.png", 0f, 1f),
			new Club("SV Darmstadt 98.jpg", 0f, 1f),
			new Club("SV Elversberg.png", 0f, 1f),
			new Club("SV Wehen Wiesbaden.png", 0f, 1f),
			new Club("SV Werder Bremen.jpg", .9f, 1f),
			new Club("TSG 1899 Hoffenheim.jpg", 0f, 1f),
			new Club("UnionBerlin.png", 1f, 1f),
			new Club("VfB Stuttgart.png", 0f, 1f),
			new Club("VfL Osnabrück.png", .93f, 1f),
			new Club("VfL Wolfsburg.jpg", .0f, 1f),
			new Club("VfL_Bochum.png", 0f, 1f),
			new Club("SSV Jahn Regensburg.png", 0f, 1f),
			new Club("Preußen Münster.png", 0f, 1f),
			new Club("SSV_Ulm_1846_Fussball.png", 0f, 1f),
	};
	
	
	private static Club findClub(String strName) {
		Club result = null;
		int iC = 0;
		do {
			Club res1 = sClubs[iC++];
			if (res1.isClub(strName)) {
				result = res1;
			}
		} while (result == null && iC < sClubs.length);
		return result;
	}
}