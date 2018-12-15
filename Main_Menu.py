# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'Main_Menu.ui'
#
# Created by: PyQt5 UI code generator 5.9.2
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtWidgets import QMessageBox, QApplication, QWidget, QInputDialog, QLineEdit, QFileDialog
import csv

class Ui_Main_Menu(object):
    def setupUi(self, Main_Menu):
        Main_Menu.setObjectName("Main_Menu")
        Main_Menu.resize(464, 261)
        Main_Menu.setMinimumSize(QtCore.QSize(464, 261))
        Main_Menu.setMaximumSize(QtCore.QSize(464, 261))
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap("Main_Menue_UI_UI\Icons\Main_Icon.png"), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        Main_Menu.setWindowIcon(icon)
        Main_Menu.setUnifiedTitleAndToolBarOnMac(False)
        self.centralwidget = QtWidgets.QWidget(Main_Menu)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayoutWidget = QtWidgets.QWidget(self.centralwidget)
        self.gridLayoutWidget.setGeometry(QtCore.QRect(20, 20, 160, 80))
        self.gridLayoutWidget.setObjectName("gridLayoutWidget")
        self.gridLayout_N = QtWidgets.QGridLayout(self.gridLayoutWidget)
        self.gridLayout_N.setContentsMargins(0, 0, 0, 0)
        self.gridLayout_N.setObjectName("gridLayout_N")
        self.horizontalSlider_N = QtWidgets.QSlider(self.gridLayoutWidget)
        self.horizontalSlider_N.setMaximum(10000)
        self.horizontalSlider_N.setProperty("value", 100)
        self.horizontalSlider_N.setOrientation(QtCore.Qt.Horizontal)
        self.horizontalSlider_N.setObjectName("horizontalSlider_N")
        self.gridLayout_N.addWidget(self.horizontalSlider_N, 3, 0, 1, 1)
        self.lineEdit_N = QtWidgets.QLineEdit(self.gridLayoutWidget)
        self.lineEdit_N.setObjectName("lineEdit_N")
        self.gridLayout_N.addWidget(self.lineEdit_N, 2, 0, 1, 1)
        self.label_N = QtWidgets.QLabel(self.gridLayoutWidget)
        self.label_N.setObjectName("label_N")
        self.gridLayout_N.addWidget(self.label_N, 1, 0, 1, 1)
        self.gridLayoutWidget_2 = QtWidgets.QWidget(self.centralwidget)
        self.gridLayoutWidget_2.setGeometry(QtCore.QRect(20, 120, 160, 80))
        self.gridLayoutWidget_2.setObjectName("gridLayoutWidget_2")
        self.gridLayout_V = QtWidgets.QGridLayout(self.gridLayoutWidget_2)
        self.gridLayout_V.setContentsMargins(0, 0, 0, 0)
        self.gridLayout_V.setObjectName("gridLayout_V")
        self.label_V = QtWidgets.QLabel(self.gridLayoutWidget_2)
        self.label_V.setObjectName("label_V")
        self.gridLayout_V.addWidget(self.label_V, 1, 0, 1, 1)
        self.horizontalSlider_V = QtWidgets.QSlider(self.gridLayoutWidget_2)
        self.horizontalSlider_V.setMaximum(10000)
        self.horizontalSlider_V.setProperty("value", 100)
        self.horizontalSlider_V.setOrientation(QtCore.Qt.Horizontal)
        self.horizontalSlider_V.setObjectName("horizontalSlider_V")
        self.gridLayout_V.addWidget(self.horizontalSlider_V, 3, 0, 1, 1)
        self.lineEdit_V = QtWidgets.QLineEdit(self.gridLayoutWidget_2)
        self.lineEdit_V.setObjectName("lineEdit_V")
        self.gridLayout_V.addWidget(self.lineEdit_V, 2, 0, 1, 1)
        self.label_V_Einheit = QtWidgets.QLabel(self.gridLayoutWidget_2)
        self.label_V_Einheit.setObjectName("label_V_Einheit")
        self.gridLayout_V.addWidget(self.label_V_Einheit, 2, 1, 1, 1)
        self.gridLayoutWidget_3 = QtWidgets.QWidget(self.centralwidget)
        self.gridLayoutWidget_3.setGeometry(QtCore.QRect(200, 20, 160, 80))
        self.gridLayoutWidget_3.setObjectName("gridLayoutWidget_3")
        self.gridLayout_T = QtWidgets.QGridLayout(self.gridLayoutWidget_3)
        self.gridLayout_T.setContentsMargins(0, 0, 0, 0)
        self.gridLayout_T.setObjectName("gridLayout_T")
        self.comboBox_T = QtWidgets.QComboBox(self.gridLayoutWidget_3)
        self.comboBox_T.setObjectName("comboBox_T")
        self.comboBox_T.addItem("")
        self.comboBox_T.addItem("")
        self.gridLayout_T.addWidget(self.comboBox_T, 2, 1, 1, 1)
        self.lineEdit_T = QtWidgets.QLineEdit(self.gridLayoutWidget_3)
        self.lineEdit_T.setObjectName("lineEdit_T")
        self.gridLayout_T.addWidget(self.lineEdit_T, 2, 0, 1, 1)
        self.horizontalSlider_T = QtWidgets.QSlider(self.gridLayoutWidget_3)
        self.horizontalSlider_T.setMaximum(10000)
        self.horizontalSlider_T.setProperty("value", 100)
        self.horizontalSlider_T.setOrientation(QtCore.Qt.Horizontal)
        self.horizontalSlider_T.setObjectName("horizontalSlider_T")
        self.gridLayout_T.addWidget(self.horizontalSlider_T, 3, 0, 1, 1)
        self.label_T = QtWidgets.QLabel(self.gridLayoutWidget_3)
        self.label_T.setObjectName("label_T")
        self.gridLayout_T.addWidget(self.label_T, 1, 0, 1, 1)
        self.gridLayoutWidget_4 = QtWidgets.QWidget(self.centralwidget)
        self.gridLayoutWidget_4.setGeometry(QtCore.QRect(200, 120, 160, 80))
        self.gridLayoutWidget_4.setObjectName("gridLayoutWidget_4")
        self.gridLayout_p = QtWidgets.QGridLayout(self.gridLayoutWidget_4)
        self.gridLayout_p.setContentsMargins(0, 0, 0, 0)
        self.gridLayout_p.setObjectName("gridLayout_p")
        self.comboBox_p = QtWidgets.QComboBox(self.gridLayoutWidget_4)
        self.comboBox_p.setObjectName("comboBox_p")
        self.comboBox_p.addItem("")
        self.comboBox_p.addItem("")
        self.comboBox_p.addItem("")
        self.gridLayout_p.addWidget(self.comboBox_p, 2, 1, 1, 1)
        self.horizontalSlider_p = QtWidgets.QSlider(self.gridLayoutWidget_4)
        self.horizontalSlider_p.setMaximum(10000)
        self.horizontalSlider_p.setProperty("value", 100)
        self.horizontalSlider_p.setOrientation(QtCore.Qt.Horizontal)
        self.horizontalSlider_p.setObjectName("horizontalSlider_p")
        self.gridLayout_p.addWidget(self.horizontalSlider_p, 3, 0, 1, 1)
        self.label_p = QtWidgets.QLabel(self.gridLayoutWidget_4)
        self.label_p.setObjectName("label_p")
        self.gridLayout_p.addWidget(self.label_p, 1, 0, 1, 1)
        self.lineEdit_p = QtWidgets.QLineEdit(self.gridLayoutWidget_4)
        self.lineEdit_p.setObjectName("lineEdit_p")
        self.gridLayout_p.addWidget(self.lineEdit_p, 2, 0, 1, 1)
        self.pushButton_Start = QtWidgets.QPushButton(self.centralwidget)
        self.pushButton_Start.setGeometry(QtCore.QRect(380, 170, 75, 23))
        self.pushButton_Start.setObjectName("pushButton_Start")
        self.gridLayoutWidget.raise_()
        self.gridLayoutWidget_2.raise_()
        self.gridLayoutWidget_3.raise_()
        self.label_T.raise_()
        self.gridLayoutWidget_4.raise_()
        self.pushButton_Start.raise_()
        Main_Menu.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(Main_Menu)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 464, 21))
        self.menubar.setObjectName("menubar")
        self.menuDatei = QtWidgets.QMenu(self.menubar)
        self.menuDatei.setObjectName("menuDatei")
        Main_Menu.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(Main_Menu)
        self.statusbar.setObjectName("statusbar")
        Main_Menu.setStatusBar(self.statusbar)
        self.actionSpeichern = QtWidgets.QAction(Main_Menu)
        self.actionSpeichern.setObjectName("actionSpeichern")
        self.action_ffnen = QtWidgets.QAction(Main_Menu)
        self.action_ffnen.setObjectName("action_ffnen")
        self.actionEinstellungen = QtWidgets.QAction(Main_Menu)
        self.actionEinstellungen.setObjectName("actionEinstellungen")
        self.menuDatei.addAction(self.actionSpeichern)
        self.menuDatei.addAction(self.action_ffnen)
        self.menuDatei.addAction(self.actionEinstellungen)
        self.menubar.addAction(self.menuDatei.menuAction())

        self.retranslateUi(Main_Menu)
        QtCore.QMetaObject.connectSlotsByName(Main_Menu)

    def retranslateUi(self, Main_Menu):
        _translate = QtCore.QCoreApplication.translate
        Main_Menu.setWindowTitle(_translate("Main_Menu", "Gas-Simulator"))
        self.lineEdit_N.setText(_translate("Main_Menu", "100"))
        self.label_N.setText(_translate("Main_Menu", "Anzahl der Teilchen"))
        self.label_V.setText(_translate("Main_Menu", "Volumen"))
        self.lineEdit_V.setText(_translate("Main_Menu", "1"))
        self.label_V_Einheit.setText(_translate("Main_Menu", "Kubikmeter"))
        self.comboBox_T.setItemText(0, _translate("Main_Menu", "K"))
        self.comboBox_T.setItemText(1, _translate("Main_Menu", "°C"))
        self.lineEdit_T.setText(_translate("Main_Menu", "293"))
        self.label_T.setText(_translate("Main_Menu", "Temperatur"))
        self.comboBox_p.setItemText(0, _translate("Main_Menu", "hPa"))
        self.comboBox_p.setItemText(1, _translate("Main_Menu", "kPa"))
        self.comboBox_p.setItemText(2, _translate("Main_Menu", "bar"))
        self.label_p.setText(_translate("Main_Menu", "Druck"))
        self.lineEdit_p.setText(_translate("Main_Menu", "293"))
        self.pushButton_Start.setText(_translate("Main_Menu", "Start"))
        self.menuDatei.setTitle(_translate("Main_Menu", "Datei"))
        self.actionSpeichern.setText(_translate("Main_Menu", "Speichern"))
        self.action_ffnen.setText(_translate("Main_Menu", "Öffnen"))
        self.actionEinstellungen.setText(_translate("Main_Menu", "Einstellungen"))

        self.update_UI(self.horizontalSlider_N, self.lineEdit_N, None)
        self.update_UI(self.horizontalSlider_p, self.lineEdit_p, None)
        self.update_UI(self.horizontalSlider_T, self.lineEdit_T, None)
        self.update_UI(self.horizontalSlider_V, self.lineEdit_V, None)

        self.update_Button(self.pushButton_Start)

        self.actionSpeichern.triggered.connect(lambda: self.save_Settings())

        self.action_ffnen.triggered.connect(lambda: self.load_Settings())

    def update_UI(self,slider,lineEdit, value_text):
            if   type(value_text) != type(None):
               slider.setValue(int(value_text))
            elif type(value_text) == type(None):
                lineEdit.textChanged.connect(lambda: slider.setValue(int(lineEdit.text())))
                slider.valueChanged.connect(lambda: lineEdit.setText(str(slider.value())))


    def update_Button(self, button):
        button.clicked.connect(lambda: print("clicked"))

    def save_Settings(self):
        name = QFileDialog.getSaveFileName()
        name = name[0]

        with open("{}.csv".format(name), "w+", newline="") as save_file:
            save_writer = csv.writer(save_file)
            save_writer.writerow(["N", "V", "T", "p"])
            save_writer.writerow([str(self.lineEdit_N.text()), str(self.lineEdit_V.text()), str(self.lineEdit_T.text()),
                                  str(self.lineEdit_p.text())])

    def load_Settings(self):
        row_array = []
        name = QFileDialog.getOpenFileName()
        name = name[0]
        try:
            with open("{}.csv".format(name), "r", newline="") as load_file:
                load_reader = csv.reader(load_file)
                for row in load_reader:
                    row_array.append(row)
        except Exception as e:
            return 0

        self.update_UI(self.horizontalSlider_N, self.lineEdit_N, str(row_array[1][0]))
        self.update_UI(self.horizontalSlider_p, self.lineEdit_p, str(row_array[1][1]))
        self.update_UI(self.horizontalSlider_T, self.lineEdit_T, str(row_array[1][2]))
        self.update_UI(self.horizontalSlider_V, self.lineEdit_V, str(row_array[1][3]))

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    Main_Menu = QtWidgets.QMainWindow()
    ui = Ui_Main_Menu()
    ui.setupUi(Main_Menu)
    Main_Menu.show()
    sys.exit(app.exec_())

