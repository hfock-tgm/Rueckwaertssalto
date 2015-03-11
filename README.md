Aufgabenstellung
JDBC: R�ckw�rtssalto
Erstelle ein Java-Programm, dass Connection-Parameter und einen Datenbanknamen auf der Kommandozeile entgegennimmt und die Struktur der Datenbank als EER-Diagramm und Relationenmodell ausgibt (in Dateien geeigneten Formats, also z.B. PNG f�r das EER und TXT f�r das RM)
Verwende dazu u.A. das ResultSetMetaData-Interface, das Methoden zur Bestimmung von Metadaten zur Verf�gung stellt.
Zum Zeichnen des EER-Diagramms kann eine beliebige Technik eingesetzt werden f�r die Java-Bibliotheken zur Verf�gung stehen: Swing, HTML5, eine WebAPI, ... . Externe Programme d�rfen nur soweit verwendet werden, als sich diese plattformunabh�ngig auf gleiche Weise ohne Aufwand (sowohl technisch als auch lizenzrechtlich!) einfach nutzen lassen. (also z.B. ein Visio-File generieren ist nicht ok, SVG ist ok, da f�r alle Plattformen geeignete Werkzeuge zur Verf�gung stehen)
Recherchiere daf�r im Internet nach geeigneten Werkzeugen.
Die Extraktion der Metadaten aus der DB muss mit Java und JDBC erfolgen.
Im EER m�ssen zumindest vorhanden sein:
o	korrekte Syntax nach Chen, MinMax oder IDEFIX
o	alle Tabellen der Datenbank als Entit�ten
o	alle Datenfelder der Tabellen als Attribute
o	Prim�rschl�ssel der Datenbanken entsprechend gekennzeichnet
o	Beziehungen zwischen den Tabellen inklusive Kardinalit�ten soweit durch Fremdschl�ssel nachvollziehbar. Sind mehrere Interpretationen m�glich, so ist nur ein (beliebiger) Fall umzusetzen: 1:n, 1:n schwach, 1:1
o	Kardinalit�ten 
Fortgeschritten (auch einzelne Punkte davon f�r Bonuspunkte umsetzbar)
o	Zusatzattribute wie UNIQUE oder NOT NULL werden beim Attributnamen dazugeschrieben, sofern diese nicht schon durch eine andere Darstellung ableitbar sind (1:1 resultiert ja in einem UNIQUE)
o	optimierte Beziehungen z.B. zwei schwache Beziehungen zu einer m:n zusammenfassen (ev. mit Attributen)
o	Erkennung von Sub/Supertyp-Beziehungen


Durch die Ausf�hrung des Programmes werden im selben Verzeichnis drei Dateien generiert.
�	Relationenmodell
o	rm.txt
�	ER-Diagramm
o	ERD.dot
o	ERD.svg
Um aus dem Dot-File eine Grafik zu machen muss Graphviz installiert sein. Anderfalls wird kein ERD.svg aus dem ERD.dot generiert.
Graphviz ist eine kostenlose Open-Source-Software, welche auf jeder g�ngigen Plattform installiert werden kann. 
Graphiz kann von der offiziellen Seite unter folgendem Link heruntergeladen werden:
�	http://www.graphviz.org/Download..php
