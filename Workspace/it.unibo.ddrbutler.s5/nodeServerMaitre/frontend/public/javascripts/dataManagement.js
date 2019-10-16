/*functions*/
function updateData(field){
  var section=document.getElementById(field[0]+"Content")
  var trovato=false;
  var rightField=(field[1]==="dish"?field[1]:field[2]);
  section.childNodes.forEach((e)=>{
    if(e.id===rightField){
      trovato=true
      if(field[4]==="put"){
        e.innerHTML=rightField + " " + (e.innerHTML.split(" ")[1]*1+parseInt(field[3]))
      }
      if(field[4]==="take"){
        e.innerHTML=rightField + " " + (e.innerHTML.split(" ")[1]*1-parseInt(field[3]))
      }
    }
  })
  if(!trovato){
    //cancello eventuale empty <p>
    section.childNodes.forEach((e)=>{
      if(e.id==="empty"){
        e.remove()
      }
    })

    var node = document.createElement("P");
    node.id=rightField
    var textnode=document.createTextNode("");
    if(field[4]==="put"){
      textnode=document.createTextNode(rightField + " " + parseInt(field[3]));
    }
    if(field[4]==="take"){
      textnode=document.createTextNode(rightField + " " + (parseInt(field[3])*-1));
    }
    node.appendChild(textnode);
    section.appendChild(node);
  }
}

function updateFridgeContent(fields){
  fields.forEach((v)=>{
    var nome=v.split(",")[0]
    var qnt=v.split(",")[1]

    var trovato=false;
    var section=document.getElementById("fridgeContent")
    section.childNodes.forEach((e)=>{
      if(e.id==nome){
        trovato=true
        e.innerHTML=`${nome} ${qnt}`
      }
    })

    if(!trovato){
      //lo aggiungo
      var node = document.createElement("P");
      var textnode=document.createTextNode(`${nome} ${qnt}`);
      node.appendChild(textnode);
      section.appendChild(node);
    }
  })
}

function loadInitParam(a,b,c,d){
    loadPantryData(a)
    loadFridgeData(b)
    loadDishwasherData(c)
    loadTableData(d)
}

function fillWithData(data,nameSection){
  var fatherDiv = document.getElementById(nameSection);
  if(data===null){
    var node = document.createElement("P");
    node.id="empty"
    var textnode = document.createTextNode("empty");
    node.appendChild(textnode);
    fatherDiv.appendChild(node);
    return
  }

  data=data.replace(/{|}|"/g,'').trim()
  var field=data.split(",")
  field.forEach((e)=>{
    var name=e.split(":")[0]
    var value=e.split(":")[1]

    var node = document.createElement("P");
    node.id=name
    var textnode = document.createTextNode(name+" "+value);
    node.appendChild(textnode);
    fatherDiv.appendChild(node);
  })
}

function loadPantryData(a){
  var initData = a//<%-JSON.stringify(pantryInitialContent) %>
  fillWithData(initData,"pantryContent")
}

function loadFridgeData(b){
  var initData = b//<%-JSON.stringify(fridgeInitialContent) %>
  fillWithData(initData,"fridgeContent")
}

function loadDishwasherData(c){
  var initData = c//<%-JSON.stringify(dishwasherInitialContent) %>
  fillWithData(initData,"dishwasherContent")
}

function loadTableData(d){
  var initData = d//<%-JSON.stringify(tableInitialContent) %>
  fillWithData(initData,"tableContent")
}
