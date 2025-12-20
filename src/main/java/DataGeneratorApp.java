
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import datageneration.Data;
import datageneration.DataBuilder;
import datageneration.Field;
import datageneration.JsScript;

public class DataGeneratorApp extends JFrame {

    private final ArrayList<FieldRow> fieldRows = new ArrayList<>();
    private final JsScript jsScript = new JsScript();

    private final JPanel infoPanel = new JPanel();
    private final JPanel dataRowsPanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();

    private final JLabel infoLabel;
    private final JButton generateButton;
    private final JButton copyJsButton;

    private final String TITLE = "Checkout Data Generator";
    private final int FRAME_WIDTH = 700;
    private final int FRAME_HEIGHT = 500;

    private final String INFO_LABEL_TEXT = "<html><i>* Impacts the generated Fiscal Code. "
            + "\"Comune di nascita\" and \"Paese di origine (dropdown)\" are mutually exclusive.</i></html>";
    private final String GENERATE_BUTTON_TEXT = "Generate";
    private final String COPY_JS_BUTTON_TEXT = "Copy JS";

    private Data generatedData;

    public DataGeneratorApp() {
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        infoLabel = new JLabel(INFO_LABEL_TEXT);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(infoLabel);

        dataRowsPanel.setLayout(new GridLayout(Field.values().length, 4, 5, 5));

        for (Field field : Field.values()) {
            FieldRow row = new FieldRow(field);
            fieldRows.add(row);

            row.addToPanel(dataRowsPanel);
        }

        generateButton = new JButton(GENERATE_BUTTON_TEXT);
        generateButton.addActionListener(e -> generateData());

        copyJsButton = new JButton(COPY_JS_BUTTON_TEXT);
        copyJsButton.addActionListener(e -> copyGeneratedDataToClipboard());

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(generateButton);
        buttonsPanel.add(copyJsButton);

        add(infoPanel, BorderLayout.NORTH);
        add(dataRowsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        generateData();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void generateData() {
        DataBuilder builder = new DataBuilder();

        for (FieldRow row : fieldRows) {
            if (!row.getCheckBox().isSelected()) {
                continue;
            }
            Field rowField = row.getField();
            String text = row.getFinalValue();

            if (rowField.getEditableClass().isEnum()) {
                Enum<?> enumValue = Enum.valueOf((Class<? extends Enum>) rowField.getEditableClass(), text);
                builder.setField(rowField, enumValue);
            } else {
                builder.setField(rowField, text);
            }
        }

        generatedData = builder
                .completeWithRandomValidData()
                .build();

        for (FieldRow row : fieldRows) {
            Field rowField = row.getField();
            String text = generatedData.hasField(rowField) ? generatedData.getField(rowField).toString() : "";
            row.getGeneratedValueTextField().setText(text);
        }
    }

    private void copyGeneratedDataToClipboard() {
        Utilities.copyTextToClipboard(jsScript.generate(generatedData));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataGeneratorApp().setVisible(true));
    }
}
