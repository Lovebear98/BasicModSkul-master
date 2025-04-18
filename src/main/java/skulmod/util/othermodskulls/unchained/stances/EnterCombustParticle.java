package skulmod.util.othermodskulls.unchained.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class EnterCombustParticle extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private float oX;
    private float oY;
    private float x;
    private float y;
    private float aV;
    private float distOffset;
    private float scaleOffset;

    public EnterCombustParticle(Color color, float x, float y) {
        this.img = ImageMaster.STRIKE_LINE;
        this.startingDuration = 0.5F;
        this.duration = this.startingDuration;
        this.color = color.cpy();
        this.rotation = MathUtils.random(360.0F);
        this.oX = x - (float)this.img.packedWidth / 2.0F + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
        this.oY = y - (float)this.img.packedHeight / 2.0F + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
        this.distOffset = MathUtils.random(800.0F, 1200.0F);
        this.renderBehind = true;
        this.aV = MathUtils.random(50.0F, 80.0F);
        this.scaleOffset = MathUtils.random(4.0F, 5.0F);
        this.aV = MathUtils.random(0.4F);
    }

    public void update() {
        if (this.aV > 0.0F) {
            this.aV -= Gdx.graphics.getDeltaTime();
        } else {
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration < 0.0F) {
                this.isDone = true;
            } else {
                this.x = this.oX + MathUtils.cosDeg(this.rotation) * this.distOffset * Interpolation.pow2In.apply(0.02F, 0.95F, this.duration * 2.0F) * Settings.scale;
                this.y = this.oY + MathUtils.sinDeg(this.rotation) * this.distOffset * Interpolation.pow3In.apply(0.02F, 0.95F, this.duration * 2.0F) * Settings.scale;
                this.duration -= Gdx.graphics.getDeltaTime();
                this.scale = this.scaleOffset * (this.duration + 0.1F) * Settings.scale;
                this.color.a = Interpolation.pow3In.apply(0.0F, 1.0F, this.duration * 2.0F);
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
