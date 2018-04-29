package BFS_DFS;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 * Created by E.E on 21.05.2017.
 * Kante mit Attributen für Verbindung zwischen den Knoten, Zeitstempel für Tiefensuche.
 */
class Kante extends Group
{
    int von;
    int zu;
    String vonKnoten;
    String zuKnoten;

    int vonKnotenHinStempel;
    int vonKnotenZurückStempel;

    int zuKnotenHinStempel;
    int zuKnotenZurückStempel;

    Kante(int von, int zu, String vonKnoten, String zuKnoten)
    {
        this( new Line(), new Line(), new Line() );
        this.von = von;
        this.zu = zu;
        this.vonKnoten = vonKnoten;
        this.zuKnoten = zuKnoten;
    }

    //Müssen public sein, da manche Effekte nur auf die Pfeilspitze angewendet werden.
    public final Line linie;
    public final Line pfeil1;
    public final Line pfeil2;
    private static final double arrowLength = 50;
    private static final double arrowWidth = 4.5;


    private Kante(Line linie, Line pfeil1, Line pfeil2)
    {
        super(linie, pfeil1, pfeil2);
        this.linie = linie;
        this.pfeil1 = pfeil1;
        this.pfeil2 = pfeil2;

        //Linie-Eigenschaften
        linie.setSmooth(true);
        linie.setStrokeWidth(1.2);
        linie.setStrokeLineCap(StrokeLineCap.ROUND);
        linie.getStrokeDashArray().addAll(20.0, 20.0);
        linie.setStroke(Color.GRAY.deriveColor(1,1,0.5, 1));

        //Pfeil-Eigenschaften
        pfeil1.setSmooth(true);
        pfeil2.setSmooth(true);
        pfeil1.setStroke(Color.GREY.deriveColor(1,1,0.5, 1));
        pfeil2.setStroke(Color.GREY.deriveColor(1,1,0.5, 1));
        pfeil1.getStrokeDashArray().addAll(20.0, 32.0);
        pfeil2.getStrokeDashArray().addAll(20.0, 32.0);
        pfeil1.setStrokeWidth(1.5);
        pfeil2.setStrokeWidth(1.5);
        pfeil1.setStrokeLineCap(StrokeLineCap.ROUND);
        pfeil2.setStrokeLineCap(StrokeLineCap.ROUND);

        InvalidationListener updater = o -> {
            double ex = getEndX();
            double ey = getEndY();
            double sx = getStartX();
            double sy = getStartY();

            pfeil1.setEndX(ex);
            pfeil1.setEndY(ey);
            pfeil2.setEndX(ex);
            pfeil2.setEndY(ey);

            if (ex == sx && ey == sy)
            {
                // Pfeile von Länge 0
                pfeil1.setStartX(ex);
                pfeil1.setStartY(ey);
                pfeil2.setStartX(ex);
                pfeil2.setStartY(ey);
            } else {
                double factor = arrowLength / Math.hypot(sx-ex, sy-ey);
                double factorO = arrowWidth / Math.hypot(sx-ex, sy-ey);

                // Part in Richtung Hauptlinie
                double dx = (sx - ex) * factor;
                double dy = (sy - ey) * factor;

                // Part ortogonal zu Hauptlinie
                double ox = (sx - ex) * factorO;
                double oy = (sy - ey) * factorO;

                pfeil1.setStartX(ex + dx - oy);
                pfeil1.setStartY(ey + dy + ox);
                pfeil2.setStartX(ex + dx + oy);
                pfeil2.setStartY(ey + dy - ox);
            }
        };

        //Properties Updater
        startXProperty().addListener(updater);
        startYProperty().addListener(updater);
        endXProperty().addListener(updater);
        endYProperty().addListener(updater);
        updater.invalidated(null);
    }

    // start/end Properties. Werden nicht alle benutzt
    public final void setStartX(double value) { linie.setStartX(value); }

    public final double getStartX() {
        return linie.getStartX();
    }

    public final DoubleProperty startXProperty() { return linie.startXProperty(); }

    public final void setStartY(double value) {
        linie.setStartY(value);
    }

    public final double getStartY() {
        return linie.getStartY();
    }

    public final DoubleProperty startYProperty() {
        return linie.startYProperty();
    }

    public final void setEndX(double value) {
        linie.setEndX(value);
    }

    public final double getEndX()
    {
        return linie.getEndX();
    }

    public final DoubleProperty endXProperty() { return linie.endXProperty(); }

    public final void setEndY(double value) {linie.setEndY(value); }

    public final double getEndY()
    {
        return linie.getEndY();
    }

    public final DoubleProperty endYProperty() {
        return linie.endYProperty();
    }
}
