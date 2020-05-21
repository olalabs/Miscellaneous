function getPersonList() {
    const xhttpReq = new XMLHttpRequest();
    //synchronic loading XML document from the file to the XML DOM object
    xhttpReq.open("GET", "person_list.xml", false);
    xhttpReq.send();

    const xmlDoc = xhttpReq.responseXML;
    const htmlTable = document.getElementById("personlistTable");
    const personNodes = xmlDoc.getElementsByTagName("person");

    let newText;
    let cellContent;
    let oneCell;

    //generating table rows in loop for all person elements
    for (let newRow = 0; newRow < personNodes.length; newRow++) {
        const oneRow = htmlTable.insertRow(-1);
        const cells = personNodes[newRow].children;
        for (let newCell = 0; newCell < cells.length; newCell++) {
            const childrenCells = cells[newCell].children;
            if(childrenCells.length > 0){ //Node has children (not a leaf)
                for(let newChildrenCell = 0; newChildrenCell < childrenCells.length; newChildrenCell++){
                    // Insert a cell in the row at the end of the table
                    oneCell = oneRow.insertCell(-1);
                    // Append a text node to the cell
                    cellContent = childrenCells[newChildrenCell].innerHTML;
                    newText = document.createTextNode(cellContent);
                    oneCell.appendChild(newText);
                }
            }else{ //Node is a leaf
                oneCell = oneRow.insertCell(-1);
                cellContent = cells[newCell].innerHTML;
                newText = document.createTextNode(cellContent);
                oneCell.appendChild(newText);
            }
        }
    }
}