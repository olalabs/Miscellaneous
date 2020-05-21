function getPersonList() {
    // loading XML document in browser
    var xhttpReq = new XMLHttpRequest();

    xhttpReq.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            getPersonList();
        }
    };

    //loading XML document from the file to the XML DOM object
    xhttpReq.open("GET", "person_list.xml", true);
    xhttpReq.send();

    var xmlDoc = xhttpReq.responseXML;
    //getting the HTML table by ID
    document.getElementById("personList").innerHTML =
        xmlDoc.getElementsByTagName("person")[0].childNodes[0].nodeValue;

}