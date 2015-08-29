#-------------------------------------------------
#
# Project created by QtCreator 2015-05-23T13:51:44
#
#-------------------------------------------------

QT       += network

QT       -= gui

DESTDIR = release

TARGET = httpServer
TEMPLATE = lib

OBJECTS_DIR=bin

DEFINES += HTTPSERVER_LIBRARY
QMAKE_CXXFLAGS += -std=c++0x

SOURCES += httpserver_impl/*.cpp


HEADERS += httpserver_impl/*.h \
            httpserver_inter/*.h

win32:CONFIG(release, debug|release): LIBS += -L$$PWD/../externalLib/release/ -lhttpdecoder
else:win32:CONFIG(debug, debug|release): LIBS += -L$$PWD/../externalLib/debug/ -lhttpdecoder
else:unix: LIBS += -L$$PWD/../externalLib/ -lhttpdecoder

INCLUDEPATH += $$PWD/../externalLib
DEPENDPATH += $$PWD/../externalLib

QMAKE_CLEAN += -r $${PWD}/$${DESTDIR}

QMAKE_POST_LINK +=$$quote(rsync -avm --include=*/ --include=*.h --exclude=* $${PWD}/$${SOURCES_DIR}/ $${PWD}/$${DESTDIR})
