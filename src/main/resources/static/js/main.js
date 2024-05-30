
const tomorrow = new Date();
tomorrow.setDate(tomorrow.getDate()+1);
tomorrow.setHours(0,0,0,0);

const interval = setInterval(()=>{
    const sub = new Date(tomorrow.getTime()-new Date().getTime());
    if(sub.getTime() <= 0)
        clearInterval(interval);
    document.getElementById("day-discount-timer").textContent = new Date(0,0,0, sub.getHours(), sub.getMinutes(), sub.getSeconds()).toLocaleTimeString();
},1000);

