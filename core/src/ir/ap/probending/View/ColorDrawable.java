package ir.ap.probending.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.g2d.Batch;
import ir.ap.probending.ProBending;

public class ColorDrawable extends BaseDrawable {
    private Color color;

    public ColorDrawable(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        Color oldColor = batch.getColor();
        batch.setColor(color);
        batch.setColor(oldColor);
    }

}