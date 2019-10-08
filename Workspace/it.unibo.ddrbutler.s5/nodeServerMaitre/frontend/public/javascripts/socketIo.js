var socket = io.connect();
socket.on('connect',function(){console.log("socket connected");});
socket.on('message',function(v){
  console.log(v)
  switch(true){
    case v.includes('notifyPrepare'):
      document.getElementById('add_foodButton').disabled=false;
      document.getElementById('clearButton').disabled=false;
      document.getElementById('foodcodeText').disabled=false;
      document.getElementById('quantityText').disabled=false;
      document.getElementById('stopButton').disabled=true;
      document.getElementById('reactivateButton').disabled=true;
      document.getElementById('input').style.display = "block";
      break;

  case v.includes('notifyAddFood'):
    document.getElementById('clearButton').disabled=false;
    document.getElementById('add_foodButton').disabled=false;
    document.getElementById('stopButton').disabled=true;
    document.getElementById('reactivateButton').disabled=true;
    break;

  case v.includes('notifyClear'):
    document.getElementById('prepareButton').disabled=false;
    document.getElementById('stopButton').disabled=true;
    document.getElementById('reactivateButton').disabled=true;
    document.getElementById('input').style.display = "none";
    break;
  case v.includes('updateContent'):
    var field=v.replace("updateContent(","").replace(")","").split(",")
    console.log(field)
    updateData(field)
    break;
  case v.includes('alert'):
    document.getElementById('alertDiv').innerHTML="Il cibo richiesto non è disponibile!"
    break;
}
});
