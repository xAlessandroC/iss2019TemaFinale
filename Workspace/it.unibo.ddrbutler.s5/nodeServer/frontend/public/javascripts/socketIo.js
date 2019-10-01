var socket = io.connect();
socket.on('connect',function(){console.log("socket connected");});
socket.on('message',function(v){
  console.log(v)
  switch(true){
    case v.includes('modelChangeMaitre(maitre,prepare'):
      document.getElementById("temp").innerHTML="prepare";
      document.getElementById('add_foodButton').disabled=false;
      document.getElementById('clearButton').disabled=false;
      document.getElementById('foodcodeText').disabled=false;
      document.getElementById('quantityText').disabled=false;
      document.getElementById('stopButton').disabled=true;
      document.getElementById('reactivateButton').disabled=true;
      break;

  case v.includes('modelChangeMaitre(maitre,add_food'):
    document.getElementById("temp").innerHTML="addFood";
    document.getElementById('clearButton').disabled=false;
    document.getElementById('add_foodButton').disabled=false;
    document.getElementById('stopButton').disabled=true;
    document.getElementById('reactivateButton').disabled=true;
    break;

  case v.includes('modelChangeMaitre(maitre,clear'):
    document.getElementById("temp").innerHTML="clear";
    document.getElementById('prepareButton').disabled=false;
    document.getElementById('stopButton').disabled=true;
    document.getElementById('reactivateButton').disabled=true;
    break;
  case v.includes('updateContent'):
    var field=v.replace("updateContent(","").replace(")","").split(",")
    console.log(field)
    updateData(field)
    break;
}
});
