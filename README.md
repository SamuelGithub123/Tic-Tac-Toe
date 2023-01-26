# Tic-Tac-Toe

Bei TicTacToe gibt es ein 3∗3 Brett, auf das die beiden Spieler abwechselnd ihre Spielsteine auf ein freies Feld legen. 

Der erste Spieler nutzt X als Spielstein, der zweite Spieler nutzt O. 

Sobald ein Spieler in einer Zeile, Spalte oder Hauptdiagonale alle Felder mit seinen Spielsteinen besetzt hat, hat dieser gewonnen. 

Wenn alle Felder belegt sind und kein Spieler gewonnen hat, endet das Spiel in einem Unentschieden.



Die Spielsteine der Spieler besitzen ab jetzt die Werte 0 bis 8. 

Jeder Spieler darf einen Spielstein nun nicht nur auf ein freies Feld legen, sondern auch auf ein Feld, auf den der Gegner einen Spielstein mit einem niedrigeren Wert gelegt hat.

Dabei kann jeder Spieler einen Wert nur einmal spielen. 

Wenn also ein Spieler den Stein mit Wert 6 spielt, darf dieser Spieler danach keinen Stein mit dem Wert 6 mehr spielen. 

Der andere Spieler darf aber noch den Stein mit Wert 6 legen.

Für die Gewinnbedingung zählen jeweils nur die Steine, die nicht von einem anderen Stein überdeckt wurden.

Sollte ein Spieler noch Spielsteine haben, allerdings diese nicht mehr legen können, hat er verloren. 

Dies kann z.B. passieren, wenn er nur noch den Spielstein mit Wert 1 hat und alle Felder entweder von Spielsteinen des Gegners mit einem höheren Wert oder eigenen Spielsteinen besetzt sind.

Jeder verbotene, falsche oder ungültige Zug (z.B. nochmal den gleichen Wert spielen) automatisch dazu führt, dass der Spieler verliert, der diesen Zug gemacht hat.

Wenn alle Felder belegt sind, endet das Spiel nicht mehr automatisch in einem Unterschieden. 

Es gibt erst dann ein Unentschieden, wenn beide Spieler all ihre Spielsteine gelegt haben und es keinen Gewinner gibt.
