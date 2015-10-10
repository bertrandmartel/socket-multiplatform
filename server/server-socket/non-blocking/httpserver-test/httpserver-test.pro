#-------------------------------------------------
#
# Project created by QtCreator 2015-05-23T13:52:17
#
#-------------------------------------------------

QT       += core
QT       += network
QT       -= gui

TARGET = httpserver-test
CONFIG   += console
CONFIG   -= app_bundle
CONFIG += debug

TEMPLATE = app

OBJECTS_DIR=bin
DESTDIR = release
QMAKE_CXXFLAGS += -std=c++0x

SOURCES += main.cpp \
    ClientSocketHandler.cpp \
    SslHandler.cpp \
    utils/*.cpp

HEADERS += \
    ClientSocketHandler.h \
    SslHandler.h \
    utils/*.h

win32:CONFIG(release, debug|release): LIBS += -L$$PWD/../httpserver/release/release/ -lhttpServer
else:win32:CONFIG(debug, debug|release): LIBS += -L$$PWD/../httpserver/release/debug/ -lhttpServer
else:unix: LIBS += -L$$PWD/../httpserver/release/ -lhttpServer

INCLUDEPATH += $$PWD/../httpserver/release
DEPENDPATH += $$PWD/../httpserver/release

win32:CONFIG(release, debug|release): LIBS += -L$$PWD/../externalLib/release/ -lhttpdecoder
else:win32:CONFIG(debug, debug|release): LIBS += -L$$PWD/../externalLib/debug/ -lhttpdecoder
else:unix: LIBS += -L$$PWD/../externalLib/ -lhttpdecoder

INCLUDEPATH += $$PWD/../externalLib
DEPENDPATH += $$PWD/../externalLib

QMAKE_CLEAN += -r $${PWD}/$${DESTDIR}

QMAKE_POST_LINK +=$$quote(rsync -avm --include=*/ --include=*.h --exclude=* $${PWD}/$${SOURCES_DIR}/ $${PWD}/$${DESTDIR})
