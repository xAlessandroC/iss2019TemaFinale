function send(task){
  console.log("onClick:"+task);
  var buttonToDisabled=task+"Button"
  document.getElementById(buttonToDisabled).disabled=true
  document.getElementById('stopButton').disabled=false
  document.getElementById('reactivateButton').disabled=false
  if(task==="add_food"){
    document.getElementById("clearButton").disabled=true
  }
  if(task==="clear"){
    document.getElementById("add_foodButton").disabled=true
  }

  //submitting with AJAX
  var foodcode=document.getElementById('foodcodeText').value
  var quantity=document.getElementById('quantityText').value
  //console.log("FOODCODE:"+foodcode+" QNT:"+quantity)
  var http = new XMLHttpRequest();
  http.open("POST", "/task");
  http.setRequestHeader( "content-type", "application/x-www-form-urlencoded" );
  http.send("task="+task+"&foodcode="+foodcode+"&quantity="+quantity)
}
