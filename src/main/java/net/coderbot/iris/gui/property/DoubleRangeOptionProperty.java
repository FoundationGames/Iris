package net.coderbot.iris.gui.property;

import net.coderbot.iris.gui.GuiUtil;
import net.coderbot.iris.gui.element.PropertyDocumentWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Properties;

public class DoubleRangeOptionProperty extends OptionProperty<Double> {
    public DoubleRangeOptionProperty(Double[] values, int defaultIndex, PropertyDocumentWidget document, String key, Text label, boolean isSlider) {
        super(values, defaultIndex, document, key, label, isSlider);
    }

    @Override
    public Text createValueText(int width) {
        return GuiUtil.trimmed(MinecraftClient.getInstance().textRenderer, Double.toString(this.getValue()), width, false, true, isDefault() ? Formatting.RESET : Formatting.YELLOW);
    }

    @Override
    public void read(Properties properties) {
        if(properties.containsKey(key)) {
            String s = properties.getProperty(key);
            try {
                this.setValue(Double.parseDouble(s));
            } catch (NumberFormatException ignored) { return; }
        }
        this.index = defaultIndex;
    }
}
