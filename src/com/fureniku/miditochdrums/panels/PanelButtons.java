package com.fureniku.miditochdrums.panels;

import com.fureniku.miditochdrums.ConverterScreen;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class PanelButtons extends PanelUI {

    public JButton saveState = new JButton("Save State");
    public JButton clear = new JButton("Clear");
    public JButton convert = new JButton("Convert!");
    public JButton copy = new JButton("Copy");
    public JButton generateFile = new JButton("Generate File");

    GridBagLayout layout = new GridBagLayout();

    public PanelButtons(ConverterScreen parent) {
        parentConstraints.gridx = 0;
        parentConstraints.gridy = 3;
        parentConstraints.gridwidth = 2;
        parentConstraints.fill = GridBagConstraints.BOTH;

        convert.setEnabled(false);
        generateFile.setEnabled(false);

        saveState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String saveStateText = PanelOptions.fullFile.isSelected() + "\n"
                        + PanelOptions.auto2xKick.isSelected() + "\n"
                        + PanelOptions.autoToms.isSelected() + "\n"

                        + PanelOptions.startPos.getText() + "\n"
                        + PanelOptions.kickTime.getText() + "\n"
                        + PanelOptions.channelId.getText() + "\n"
                        + PanelOptions.midiTicks.getText() + "\n"
                        + PanelOptions.chTicks.getText() + "\n"
                        + PanelOptions.ghostThreshold.getText() + "\n"

                        + PanelNotes.kickText.getText() + "\n"
                        + PanelNotes.redText.getText() + "\n"
                        + PanelNotes.yellowText.getText() + "\n"
                        + PanelNotes.blueText.getText() + "\n"
                        + PanelNotes.greenText.getText() + "\n"
                        + PanelNotes.cymbalYellowText.getText() + "\n"
                        + PanelNotes.cymbalBlueText.getText() + "\n"
                        + PanelNotes.cymbalGreenText.getText() + "\n"
                        + PanelNotes.ignoredText.getText();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                fileChooser.setSelectedFile(new File("myState.txt"));
                fileChooser.setFileFilter(new FileNameExtensionFilter(".txt file", "txt", "TXT"));

                int userSelection = fileChooser.showSaveDialog(parent);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        FileWriter writer = new FileWriter(fileToSave);
                        writer.write(saveStateText);
                        writer.close();
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.convert();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.clear();
                generateFile.setText("Generate File");
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(parent.getOutput());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        generateFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateFile.setText("File generated!");
            }
        });

        /*this.setLayout(layout);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.insets = new Insets(10,10,10,5);
        c.anchor = GridBagConstraints.CENTER;
        this.add(clear, c);
        
        c.gridx = 1;
        c.insets = new Insets(10,5,10,5);
        c.anchor = GridBagConstraints.CENTER;
        this.add(convert, c);
        
        c.gridx = 2;
        c.insets = new Insets(10,5,10,10);
        c.anchor = GridBagConstraints.CENTER;
        this.add(copy, c);*/

        this.add(saveState);
        this.add(clear);
        this.add(convert);
        this.add(copy);
        //this.add(generateFile);
    }
}
