
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import datageneration.Field;
import lombok.Getter;

@Getter
public class FieldRow {

    private final JLabel label;
    private final JTextField generatedValueTextField;
    private final JCheckBox checkBox;

    private final JTextField editableValueTextField;
    private final JComboBox<String> dropdown;

    private final Field field;
    private final boolean usesEnumDropdown;

    private final int HIGHLIGHT_DELAY = 500;

    @SuppressWarnings("unchecked")
    public FieldRow(Field field) {
        this.field = field;

        this.label = new JLabel(field.getLabel(), SwingConstants.RIGHT);
        this.generatedValueTextField = new JTextField();
        this.generatedValueTextField.setEditable(false);
        this.generatedValueTextField.setBackground(Color.WHITE);

        this.checkBox = new JCheckBox("Use provided value:");
        checkBox.addActionListener(e -> toggleEditable());

        this.usesEnumDropdown = field.getEditableClass().isEnum();

        if (this.usesEnumDropdown) {
            this.dropdown = new JComboBox<>(getEnumValues((Class<? extends Enum<?>>) field.getEditableClass()));
            this.dropdown.setEnabled(false);
            this.editableValueTextField = null;
        } else {
            this.editableValueTextField = new JTextField();
            this.editableValueTextField.setEnabled(false);
            this.editableValueTextField.setBackground(Color.LIGHT_GRAY);
            this.dropdown = null;
        }
        this.generatedValueTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utilities.copyTextToClipboard(generatedValueTextField.getText());
                highlightField(generatedValueTextField);
            }
        });
    }

    public void addToPanel(JPanel panel) {
        panel.add(label);
        panel.add(generatedValueTextField);
        panel.add(checkBox);
        panel.add(usesEnumDropdown ? dropdown : editableValueTextField);
    }

    public void toggleEditable() {
        boolean checked = checkBox.isSelected();
        if (usesEnumDropdown) {
            dropdown.setEnabled(checked);
        } else {
            editableValueTextField.setEnabled(checked);
            editableValueTextField.setBackground(checked ? Color.WHITE : Color.LIGHT_GRAY);
        }
        generatedValueTextField.setBackground(checked ? Color.LIGHT_GRAY : Color.WHITE);
    }

    public String getFinalValue() {
        return checkBox.isSelected()
                ? usesEnumDropdown ? (String) dropdown.getSelectedItem() : editableValueTextField.getText()
                : generatedValueTextField.getText();
    }

    private String[] getEnumValues(Class<? extends Enum<?>> enumClass) {
        return enumClass == null
                ? new String[]{}
                : Arrays.stream(enumClass.getEnumConstants())
                        .map(Enum::name)
                        .toArray(String[]::new);
    }

    private void highlightField(JTextField field) {
        Color originalColor = field.getBackground();
        field.setBackground(Color.YELLOW);
        Timer timer = new Timer(HIGHLIGHT_DELAY, e -> field.setBackground(originalColor));
        timer.setRepeats(false);
        timer.start();
    }
}
