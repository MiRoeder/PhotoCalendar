package de.dreiroeders.fotokalender;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MyFussballBundesliga {

	public static void addMyFussballBundesliga(FotoKalender cal) {
		//		SPIELPLAN
		//		BUNDESLIGA
		//		SAISON 2024/2025
		//		04.07.2024
		//		DFL Deutsche Fußball Liga e.V.
		//		Sämtliche Rechte an dem Spielplan liegen bei DFL Deutsche Fußball Liga e.V.
		//		Änderungen des Spielplans bleiben vorbehalten.
		//		Alle weiteren Informationen zur Saison 2024/2025 unter bundesliga.de
		addTermin(cal,23, 8,23, 8, 2024, " 1 1 Borussia Mönchengladbach Bayer 04 Leverkusen"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 2 RB Leipzig VfL Bochum 1848"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 3 Borussia Dortmund Eintracht Frankfurt"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 4 TSG Hoffenheim Holstein Kiel"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 5 Sport-Club Freiburg VfB Stuttgart"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 6 FC Augsburg SV Werder Bremen"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 7 VfL Wolfsburg FC Bayern München"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 8 1_FSV Mainz 05 1_FC Union Berlin"); // 
		addTermin(cal,24, 8,25, 8, 2024, " 1 9 FC St_ Pauli 1_FC Heidenheim 1846"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 10 Bayer 04 Leverkusen RB Leipzig"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 11 VfB Stuttgart 1_FSV Mainz 05"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 12 FC Bayern München Sport-Club Freiburg"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 13 Eintracht Frankfurt TSG Hoffenheim"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 14 1_FC Heidenheim 1846 FC Augsburg"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 15 SV Werder Bremen Borussia Dortmund"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 16 1_FC Union Berlin FC St_ Pauli"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 17 VfL Bochum 1848 Borussia Mönchengladbach"); // 
		addTermin(cal,30, 8, 1, 9, 2024, " 2 18 Holstein Kiel VfL Wolfsburg"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 19 RB Leipzig 1_FC Union Berlin"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 20 Borussia Dortmund 1_FC Heidenheim 1846"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 21 TSG Hoffenheim Bayer 04 Leverkusen"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 22 Sport-Club Freiburg VfL Bochum 1848"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 23 FC Augsburg FC St_ Pauli"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 24 VfL Wolfsburg Eintracht Frankfurt"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 25 1_FSV Mainz 05 SV Werder Bremen"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 26 Borussia Mönchengladbach VfB Stuttgart"); // 
		addTermin(cal,13, 9,15, 9, 2024, " 3 27 Holstein Kiel FC Bayern München"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 28 Bayer 04 Leverkusen VfL Wolfsburg"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 29 VfB Stuttgart Borussia Dortmund"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 30 Eintracht Frankfurt Borussia Mönchengladbach"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 31 1_FC Heidenheim 1846 Sport-Club Freiburg"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 32 SV Werder Bremen FC Bayern München"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 33 FC Augsburg 1_FSV Mainz 05"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 34 1_FC Union Berlin TSG Hoffenheim"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 35 VfL Bochum 1848 Holstein Kiel"); // 
		addTermin(cal,20, 9,22, 9, 2024, " 4 36 FC St_ Pauli RB Leipzig"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 37 FC Bayern München Bayer 04 Leverkusen"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 38 RB Leipzig FC Augsburg"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 39 Borussia Dortmund VfL Bochum 1848"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 40 TSG Hoffenheim SV Werder Bremen"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 41 Sport-Club Freiburg FC St_ Pauli"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 42 VfL Wolfsburg VfB Stuttgart"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 43 1_FSV Mainz 05 1_FC Heidenheim 1846"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 44 Borussia Mönchengladbach 1_FC Union Berlin"); // 
		addTermin(cal,27, 9,29, 9, 2024, " 5 45 Holstein Kiel Eintracht Frankfurt"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 46 Bayer 04 Leverkusen Holstein Kiel"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 47 VfB Stuttgart TSG Hoffenheim"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 48 Eintracht Frankfurt FC Bayern München"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 49 1_FC Heidenheim 1846 RB Leipzig"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 50 SV Werder Bremen Sport-Club Freiburg"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 51 FC Augsburg Borussia Mönchengladbach"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 52 1_FC Union Berlin Borussia Dortmund"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 53 VfL Bochum 1848 VfL Wolfsburg"); // 
		addTermin(cal, 4,10, 6,10, 2024, " 6 54 FC St_ Pauli 1_FSV Mainz 05"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 55 Bayer 04 Leverkusen Eintracht Frankfurt"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 56 FC Bayern München VfB Stuttgart"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 57 Borussia Dortmund FC St_ Pauli"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 58 TSG Hoffenheim VfL Bochum 1848"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 59 Sport-Club Freiburg FC Augsburg"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 60 VfL Wolfsburg SV Werder Bremen"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 61 1_FSV Mainz 05 RB Leipzig"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 62 Borussia Mönchengladbach 1_FC Heidenheim 1846"); // 
		addTermin(cal,18,10,20,10, 2024, " 7 63 Holstein Kiel 1_FC Union Berlin"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 64 VfB Stuttgart Holstein Kiel"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 65 RB Leipzig Sport-Club Freiburg"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 66 1_FC Heidenheim 1846 TSG Hoffenheim"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 67 SV Werder Bremen Bayer 04 Leverkusen"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 68 FC Augsburg Borussia Dortmund"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 69 1_FSV Mainz 05 Borussia Mönchengladbach"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 70 1_FC Union Berlin Eintracht Frankfurt"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 71 VfL Bochum 1848 FC Bayern München"); // 
		addTermin(cal,25,10,27,10, 2024, " 8 72 FC St_ Pauli VfL Wolfsburg"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 73 Bayer 04 Leverkusen VfB Stuttgart"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 74 FC Bayern München 1_FC Union Berlin"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 75 Borussia Dortmund RB Leipzig"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 76 Eintracht Frankfurt VfL Bochum 1848"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 77 TSG Hoffenheim FC St_ Pauli"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 78 Sport-Club Freiburg 1_FSV Mainz 05"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 79 VfL Wolfsburg FC Augsburg"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 80 Borussia Mönchengladbach SV Werder Bremen"); // 
		addTermin(cal, 1,11, 3,11, 2024, " 9 81 Holstein Kiel 1_FC Heidenheim 1846"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 82 VfB Stuttgart Eintracht Frankfurt"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 83 RB Leipzig Borussia Mönchengladbach"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 84 1_FC Heidenheim 1846 VfL Wolfsburg"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 85 SV Werder Bremen Holstein Kiel"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 86 FC Augsburg TSG Hoffenheim"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 87 1_FSV Mainz 05 Borussia Dortmund"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 88 1_FC Union Berlin Sport-Club Freiburg"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 89 VfL Bochum 1848 Bayer 04 Leverkusen"); // 
		addTermin(cal, 8,11,10,11, 2024, " 10 90 FC St_ Pauli FC Bayern München"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 91 Bayer 04 Leverkusen 1_FC Heidenheim 1846"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 92 VfB Stuttgart VfL Bochum 1848"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 93 FC Bayern München FC Augsburg"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 94 Borussia Dortmund Sport-Club Freiburg"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 95 Eintracht Frankfurt SV Werder Bremen"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 96 TSG Hoffenheim RB Leipzig"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 97 VfL Wolfsburg 1_FC Union Berlin"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 98 Borussia Mönchengladbach FC St_ Pauli"); // 
		addTermin(cal,22,11,24,11, 2024, " 11 99 Holstein Kiel 1_FSV Mainz 05"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 100 RB Leipzig VfL Wolfsburg"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 101 Borussia Dortmund FC Bayern München"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 102 1_FC Heidenheim 1846 Eintracht Frankfurt"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 103 SV Werder Bremen VfB Stuttgart"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 104 Sport-Club Freiburg Borussia Mönchengladbach"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 105 FC Augsburg VfL Bochum 1848"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 106 1_FSV Mainz 05 TSG Hoffenheim"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 107 1_FC Union Berlin Bayer 04 Leverkusen"); // 
		addTermin(cal,29,11, 1,12, 2024, " 12 108 FC St_ Pauli Holstein Kiel"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 109 Bayer 04 Leverkusen FC St_ Pauli"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 110 VfB Stuttgart 1_FC Union Berlin"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 111 FC Bayern München 1_FC Heidenheim 1846"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 112 Eintracht Frankfurt FC Augsburg"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 113 TSG Hoffenheim Sport-Club Freiburg"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 114 VfL Wolfsburg 1_FSV Mainz 05"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 115 Borussia Mönchengladbach Borussia Dortmund"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 116 VfL Bochum 1848 SV Werder Bremen"); // 
		addTermin(cal, 6,12, 8,12, 2024, " 13 117 Holstein Kiel RB Leipzig"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 118 RB Leipzig Eintracht Frankfurt"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 119 Borussia Dortmund TSG Hoffenheim"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 120 1_FC Heidenheim 1846 VfB Stuttgart"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 121 Sport-Club Freiburg VfL Wolfsburg"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 122 FC Augsburg Bayer 04 Leverkusen"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 123 1_FSV Mainz 05 FC Bayern München"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 124 Borussia Mönchengladbach Holstein Kiel"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 125 1_FC Union Berlin VfL Bochum 1848"); // 
		addTermin(cal,13,12,15,12, 2024, " 14 126 FC St_ Pauli SV Werder Bremen"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 127 Bayer 04 Leverkusen Sport-Club Freiburg"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 128 VfB Stuttgart FC St_ Pauli"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 129 FC Bayern München RB Leipzig"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 130 Eintracht Frankfurt 1_FSV Mainz 05"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 131 TSG Hoffenheim Borussia Mönchengladbach"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 132 SV Werder Bremen 1_FC Union Berlin"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 133 VfL Wolfsburg Borussia Dortmund"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 134 VfL Bochum 1848 1_FC Heidenheim 1846"); // 
		addTermin(cal,20,12,22,12, 2024, " 15 135 Holstein Kiel FC Augsburg"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 136 RB Leipzig SV Werder Bremen"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 137 Borussia Dortmund Bayer 04 Leverkusen"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 138 TSG Hoffenheim VfL Wolfsburg"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 139 1_FC Heidenheim 1846 1_FC Union Berlin"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 140 Sport-Club Freiburg Holstein Kiel"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 141 FC Augsburg VfB Stuttgart"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 142 1_FSV Mainz 05 VfL Bochum 1848"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 143 Borussia Mönchengladbach FC Bayern München"); // 
		addTermin(cal,10, 1,12, 1, 2025, " 16 144 FC St_ Pauli Eintracht Frankfurt"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 145 Bayer 04 Leverkusen 1_FSV Mainz 05"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 146 VfB Stuttgart RB Leipzig"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 147 FC Bayern München TSG Hoffenheim"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 148 Eintracht Frankfurt Sport-Club Freiburg"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 149 SV Werder Bremen 1_FC Heidenheim 1846"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 150 VfL Wolfsburg Borussia Mönchengladbach"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 151 1_FC Union Berlin FC Augsburg"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 152 VfL Bochum 1848 FC St_ Pauli"); // 
		addTermin(cal,14, 1,15, 1, 2025, " 17 153 Holstein Kiel Borussia Dortmund"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 154 Bayer 04 Leverkusen Borussia Mönchengladbach"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 155 VfB Stuttgart Sport-Club Freiburg"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 156 FC Bayern München VfL Wolfsburg"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 157 Eintracht Frankfurt Borussia Dortmund"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 158 1_FC Heidenheim 1846 FC St_ Pauli"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 159 SV Werder Bremen FC Augsburg"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 160 1_FC Union Berlin 1_FSV Mainz 05"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 161 VfL Bochum 1848 RB Leipzig"); // 
		addTermin(cal,17, 1,19, 1, 2025, " 18 162 Holstein Kiel TSG Hoffenheim"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 163 RB Leipzig Bayer 04 Leverkusen"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 164 Borussia Dortmund SV Werder Bremen"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 165 TSG Hoffenheim Eintracht Frankfurt"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 166 Sport-Club Freiburg FC Bayern München"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 167 FC Augsburg 1_FC Heidenheim 1846"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 168 VfL Wolfsburg Holstein Kiel"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 169 1_FSV Mainz 05 VfB Stuttgart"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 170 Borussia Mönchengladbach VfL Bochum 1848"); // 
		addTermin(cal,24, 1,26, 1, 2025, " 19 171 FC St_ Pauli 1_FC Union Berlin"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 172 Bayer 04 Leverkusen TSG Hoffenheim"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 173 VfB Stuttgart Borussia Mönchengladbach"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 174 FC Bayern München Holstein Kiel"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 175 Eintracht Frankfurt VfL Wolfsburg"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 176 1_FC Heidenheim 1846 Borussia Dortmund"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 177 SV Werder Bremen 1_FSV Mainz 05"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 178 1_FC Union Berlin RB Leipzig"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 179 VfL Bochum 1848 Sport-Club Freiburg"); // 
		addTermin(cal,31, 1, 2, 2, 2025, " 20 180 FC St_ Pauli FC Augsburg"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 181 FC Bayern München SV Werder Bremen"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 182 RB Leipzig FC St_ Pauli"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 183 Borussia Dortmund VfB Stuttgart"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 184 TSG Hoffenheim 1_FC Union Berlin"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 185 Sport-Club Freiburg 1_FC Heidenheim 1846"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 186 VfL Wolfsburg Bayer 04 Leverkusen"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 187 1_FSV Mainz 05 FC Augsburg"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 188 Borussia Mönchengladbach Eintracht Frankfurt"); // 
		addTermin(cal, 7, 2, 9, 2, 2025, " 21 189 Holstein Kiel VfL Bochum 1848"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 190 Bayer 04 Leverkusen FC Bayern München"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 191 VfB Stuttgart VfL Wolfsburg"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 192 Eintracht Frankfurt Holstein Kiel"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 193 1_FC Heidenheim 1846 1_FSV Mainz 05"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 194 SV Werder Bremen TSG Hoffenheim"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 195 FC Augsburg RB Leipzig"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 196 1_FC Union Berlin Borussia Mönchengladbach"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 197 VfL Bochum 1848 Borussia Dortmund"); // 
		addTermin(cal,14, 2,16, 2, 2025, " 22 198 FC St_ Pauli Sport-Club Freiburg"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 199 FC Bayern München Eintracht Frankfurt"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 200 RB Leipzig 1_FC Heidenheim 1846"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 201 Borussia Dortmund 1_FC Union Berlin"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 202 TSG Hoffenheim VfB Stuttgart"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 203 Sport-Club Freiburg SV Werder Bremen"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 204 VfL Wolfsburg VfL Bochum 1848"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 205 1_FSV Mainz 05 FC St_ Pauli"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 206 Borussia Mönchengladbach FC Augsburg"); // 
		addTermin(cal,21, 2,23, 2, 2025, " 23 207 Holstein Kiel Bayer 04 Leverkusen"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 208 VfB Stuttgart FC Bayern München"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 209 RB Leipzig 1_FSV Mainz 05"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 210 Eintracht Frankfurt Bayer 04 Leverkusen"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 211 1_FC Heidenheim 1846 Borussia Mönchengladbach"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 212 SV Werder Bremen VfL Wolfsburg"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 213 FC Augsburg Sport-Club Freiburg"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 214 1_FC Union Berlin Holstein Kiel"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 215 VfL Bochum 1848 TSG Hoffenheim"); // 
		addTermin(cal,28, 2, 2, 3, 2025, " 24 216 FC St_ Pauli Borussia Dortmund"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 217 Bayer 04 Leverkusen SV Werder Bremen"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 218 FC Bayern München VfL Bochum 1848"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 219 Borussia Dortmund FC Augsburg"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 220 Eintracht Frankfurt 1_FC Union Berlin"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 221 TSG Hoffenheim 1_FC Heidenheim 1846"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 222 Sport-Club Freiburg RB Leipzig"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 223 VfL Wolfsburg FC St_ Pauli"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 224 Borussia Mönchengladbach 1_FSV Mainz 05"); // 
		addTermin(cal, 7, 3, 9, 3, 2025, " 25 225 Holstein Kiel VfB Stuttgart"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 226 VfB Stuttgart Bayer 04 Leverkusen"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 227 RB Leipzig Borussia Dortmund"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 228 1_FC Heidenheim 1846 Holstein Kiel"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 229 SV Werder Bremen Borussia Mönchengladbach"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 230 FC Augsburg VfL Wolfsburg"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 231 1_FSV Mainz 05 Sport-Club Freiburg"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 232 1_FC Union Berlin FC Bayern München"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 233 VfL Bochum 1848 Eintracht Frankfurt"); // 
		addTermin(cal,14, 3,16, 3, 2025, " 26 234 FC St_ Pauli TSG Hoffenheim"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 235 Bayer 04 Leverkusen VfL Bochum 1848"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 236 FC Bayern München FC St_ Pauli"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 237 Borussia Dortmund 1_FSV Mainz 05"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 238 Eintracht Frankfurt VfB Stuttgart"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 239 TSG Hoffenheim FC Augsburg"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 240 Sport-Club Freiburg 1_FC Union Berlin"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 241 VfL Wolfsburg 1_FC Heidenheim 1846"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 242 Borussia Mönchengladbach RB Leipzig"); // 
		addTermin(cal,28, 3,30, 3, 2025, " 27 243 Holstein Kiel SV Werder Bremen"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 244 RB Leipzig TSG Hoffenheim"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 245 1_FC Heidenheim 1846 Bayer 04 Leverkusen"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 246 SV Werder Bremen Eintracht Frankfurt"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 247 Sport-Club Freiburg Borussia Dortmund"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 248 FC Augsburg FC Bayern München"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 249 1_FSV Mainz 05 Holstein Kiel"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 250 1_FC Union Berlin VfL Wolfsburg"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 251 VfL Bochum 1848 VfB Stuttgart"); // 
		addTermin(cal, 4, 4, 6, 4, 2025, " 28 252 FC St_ Pauli Borussia Mönchengladbach"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 253 Bayer 04 Leverkusen 1_FC Union Berlin"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 254 VfB Stuttgart SV Werder Bremen"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 255 FC Bayern München Borussia Dortmund"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 256 Eintracht Frankfurt 1_FC Heidenheim 1846"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 257 TSG Hoffenheim 1_FSV Mainz 05"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 258 VfL Wolfsburg RB Leipzig"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 259 Borussia Mönchengladbach Sport-Club Freiburg"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 260 VfL Bochum 1848 FC Augsburg"); // 
		addTermin(cal,11, 4,13, 4, 2025, " 29 261 Holstein Kiel FC St_ Pauli"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 262 RB Leipzig Holstein Kiel"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 263 Borussia Dortmund Borussia Mönchengladbach"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 264 1_FC Heidenheim 1846 FC Bayern München"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 265 SV Werder Bremen VfL Bochum 1848"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 266 Sport-Club Freiburg TSG Hoffenheim"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 267 FC Augsburg Eintracht Frankfurt"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 268 1_FSV Mainz 05 VfL Wolfsburg"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 269 1_FC Union Berlin VfB Stuttgart"); // 
		addTermin(cal,19, 4,20, 4, 2025, " 30 270 FC St_ Pauli Bayer 04 Leverkusen"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 271 Bayer 04 Leverkusen FC Augsburg"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 272 VfB Stuttgart 1_FC Heidenheim 1846"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 273 FC Bayern München 1_FSV Mainz 05"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 274 Eintracht Frankfurt RB Leipzig"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 275 TSG Hoffenheim Borussia Dortmund"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 276 SV Werder Bremen FC St_ Pauli"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 277 VfL Wolfsburg Sport-Club Freiburg"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 278 VfL Bochum 1848 1_FC Union Berlin"); // 
		addTermin(cal,25, 4,27, 4, 2025, " 31 279 Holstein Kiel Borussia Mönchengladbach"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 280 RB Leipzig FC Bayern München"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 281 Borussia Dortmund VfL Wolfsburg"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 282 1_FC Heidenheim 1846 VfL Bochum 1848"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 283 Sport-Club Freiburg Bayer 04 Leverkusen"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 284 FC Augsburg Holstein Kiel"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 285 1_FSV Mainz 05 Eintracht Frankfurt"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 286 Borussia Mönchengladbach TSG Hoffenheim"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 287 1_FC Union Berlin SV Werder Bremen"); // 
		addTermin(cal, 2, 5, 4, 5, 2025, " 32 288 FC St_ Pauli VfB Stuttgart"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 289 Bayer 04 Leverkusen Borussia Dortmund"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 290 VfB Stuttgart FC Augsburg"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 291 FC Bayern München Borussia Mönchengladbach"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 292 Eintracht Frankfurt FC St_ Pauli"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 293 SV Werder Bremen RB Leipzig"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 294 VfL Wolfsburg TSG Hoffenheim"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 295 1_FC Union Berlin 1_FC Heidenheim 1846"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 296 VfL Bochum 1848 1_FSV Mainz 05"); // 
		addTermin(cal, 9, 5,11, 5, 2025, " 33 297 Holstein Kiel Sport-Club Freiburg"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 298 RB Leipzig VfB Stuttgart"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 299 Borussia Dortmund Holstein Kiel"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 300 TSG Hoffenheim FC Bayern München"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 301 1_FC Heidenheim 1846 SV Werder Bremen"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 302 Sport-Club Freiburg Eintracht Frankfurt"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 303 FC Augsburg 1_FC Union Berlin"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 304 1_FSV Mainz 05 Bayer 04 Leverkusen"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 305 Borussia Mönchengladbach VfL Wolfsburg"); // 
		addTermin(cal,17, 5,17, 5, 2025, " 34 306 FC St_ Pauli VfL Bochum 1848"); // 
		
		if (cal.THIS_YEAR == 2025) {
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
		int nWeekDay = Calendar.SATURDAY;
		float deltaY;
		if (nDays == 0) {
			deltaY = 0f;
		} else {
			nWeekDay = lastDay.get(Calendar.DAY_OF_WEEK);
			if      (nDays == 1 && nWeekDay == Calendar.WEDNESDAY) deltaY = -4f/9f;  // 4 Spiele am Dienstag
			else if (nDays == 1 && nWeekDay == Calendar.SUNDAY)    deltaY = -6f/8f;  // 6 Spiele am Samstag
			else if (nDays == 2 && nWeekDay == Calendar.SUNDAY)    deltaY = -(2*1+6)/9f; // 1 Spiel am Freitag + 6 Spiele am Samstag
			else if (nDays == 2 && nWeekDay == Calendar.MONDAY)    deltaY = -(2*6+2)/9f; // 6 Spiele am Samstag + 2 Spiele am Sonntag
			else if (nDays == 3 && nWeekDay == Calendar.MONDAY)    deltaY = -(3*1+2*5+2)/9f; // 1 Spiel am Freitag + 5 Spiele am Samstag + 2 Spiele am Sonntag
			else												   deltaY = -nDays/2f;
		}
		File filHeim = new File("res/"+strHeimIcoName);
		File filAusw = new File("res/"+strAuswIcoName);
		if (firstDay.get(Calendar.MONTH) == lastDay.get(Calendar.MONTH)) {
			ArrayList<PersonalDate> alreadyEvents = cal.mDates.getCalEvents(lastDay);
			deltaY += alreadyEvents.size() * 0.45f;
			PersonalDate sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, factor, deltaY, lastDay);
			cal.mDates.addCalEvent(sp);
			for (int iD = 1; iD <= nDays; ++iD) {
				cal.mDates.addCalEvent01(PersonalDate.createDate("{0,date,EE} ", sp.getDate(), -iD, (byte)0));
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
			new Club("Dynamo Dresden.png", .92f, 1f),
			new Club("Eintr Frankfurt.jpg", 0f, 1f),
			new Club("Eintracht Braunschweig.png", 0f, 1f),
			new Club("FC Augsburg.jpg", 0f, 1f),
			new Club("FC Bayern München.jpg", 0f, 1f),
			new Club("FC Heidenheim 1846.png", 0f, 1f),
			new Club("FC Ingolstadt 04.jpg", 0f, 1f),
			new Club("FC Köln.jpg", 0f, 1f),
			new Club("FC Kaiserslautern.png", 0f, 1f),
			new Club("FC Magdeburg.png", .93f, 1f),
			new Club("FC St. Pauli.png", .92f, 1f),
			new Club("Fortuna_Düsseldorf.svg.png", 0f, 1f),
			new Club("FSV Mainz 05.jpg", 0f, 1f),
			new Club("Hamburger SV.jpg", .93f, 1f),
			new Club("Hannover 96.jpg", .94f, 1f),
			new Club("Hansa Rostock.png", .93f, 1f),
			new Club("Hertha BSC.jpg", 2f, 1.1f),
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
			new Club("SV Werder Bremen.jpg", .93f, 1f),
			new Club("TSG 1899 Hoffenheim.jpg", 0f, 1f),
			new Club("UnionBerlin.png", 1.01f, 1f),
			new Club("VfB Stuttgart.png", 0f, 1f),
			new Club("VfL Osnabrück.png", .93f, 1f),
			new Club("VfL Wolfsburg.jpg", .93f, 1f),
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