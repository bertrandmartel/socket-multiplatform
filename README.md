# Multi-platform Socket connection implementations (In construction)


This project will features socket connection implementation on multiple platforms and frameworks :

<table>
    <tr>
        <td colspan="6"></td>
        <td colspan="2"><b>Java</b></td>
        <td colspan="2"><b>JS browser</b></td>
        <td colspan="2"><b>C++ QT4</b></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="4">TCP Server socket</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2" rowspan="8"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="4">Server websocket</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">TCP Client socket</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2" rowspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">Client websocket</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
</table>

List of external projects :

* Websocket C++ QT4 :  http://akinaru.github.io/websocket-non-blocking-cpp/

* Websocket JAVA    :  http://akinaru.github.io/websocket-java/
