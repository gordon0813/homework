clear all 
clc

h=[1,0.5,0.1,0.05,0.01,0.005]
c=4
function f=sin_x3(H)
  c=4
  f=sin((c+H))/(c+H)^3;
endfunction
Truegradient=(cos(c)*c^3-3*sin(c)*c^2)/c^6
gradient1H=[];
gradient2H=[];
gradient4H=[];
shape=size(h)(2)
for i=1:shape
  gradient1H=[gradient1H,(sin_x3(h(i))-sin_x3(0))/h(i)];
end
for i=1:shape
  gradient2H=[gradient2H,(sin_x3(h(i))-sin_x3(-h(i)))/(h(i)*2)];
endfor
for i=1:shape
  gradient4H=[gradient4H,(-sin_x3(h(i)*2)+8*sin_x3(h(i))-8*sin_x3(-h(i))+sin_x3(-h(i)*2))/(12*h(i))];
endfor
error2h=log10(abs(Truegradient-gradient2H))
error1h=log10(abs(Truegradient-gradient1H))
error4h=log10(abs(Truegradient-gradient4H))
x=log10(h)
plot(x,error1h,"r-*")
hold
plot(x,error2h,"g-o")
plot(x,error4h,"b-+")
print("-djpeg",["pictured_A",'.jpg'])
