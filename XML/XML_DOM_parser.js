function getPersonList() {
    const xhttpReq = new XMLHttpRequest();
    //synchronic loading XML document from the file to the XML DOM object
    xhttpReq.open("GET", "person_list.xml", false);
    xhttpReq.send();

    const xmlDoc = xhttpReq.responseXML;
    const htmlTable = document.getElementById("personlistTable");
    const personNodes = xmlDoc.getElementsByTagName("person");

    //generating table rows in loop for all person elements
    for (let newRow = 0; newRow < personNodes.length; newRow++) {
        // Insert a row at the end of the table
        const oneRow = htmlTable.insertRow(-1);
        var cells = personNodes[newRow].children;
        for (var newCell = 0; newCell < cells.length; newCell++) {
            checkDescendants(cells[newCell].children, oneRow)
        }
    }

    function checkDescendants(childrenCells, oneRow){
        if(childrenCells.length > 0){ //Node has children (not a leaf)
            for(let newChildrenCell = 0; newChildrenCell < childrenCells.length; newChildrenCell++){
                generateCell(childrenCells[newChildrenCell], oneRow)
            }
        }else{ //Node is a leaf
            generateCell(cells[newCell], oneRow)
        }
    }

    function generateCell(indexCell, oneRow){
        // Insert a cell in the row at the end of the table
        let oneCell = oneRow.insertCell(-1);
        // Append a text node to the cell
        let cellContent = indexCell.innerHTML;
        let newText = document.createTextNode(cellContent);
        oneCell.appendChild(newText);
    }
}