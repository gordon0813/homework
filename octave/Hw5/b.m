clear all 
clc
function re=f(x) 
  re=2+0.01*x.^2
end
sep=1
x01=[0:sep:15]
x05=[0,0.5,15];
x10=[0,1,15];

y=[4]
for i=1:length(x01)-1;
  y=[y,y(1,i)-sep*y(1,i)*f(x01(1,i))];
end
plot(x01,y,"r");
hold;
yback=[4];
for i=1:length(x01)-1;
  yback=[yback,yback(1,i)/(1+sep*f(x01(1,i+1)))];
end;
plot(x01,yback,"g");
yt=[4]
for i=1:length(x01)-1;
  yt=[yt,(yt(1,i)-sep*yt(1,i)*1/2*f(x01(1,i)))/(1+1/2*sep*f(x01(1,i+1)))];
end
plot(x01,yt);
yrq2=[4];
for i=1:length(x01)-1
  k1=sep*yrq2(1,i)*(-f(x01(1,i)));
  k2=sep*(yrq2(1,i)+0.5*k1)*(-f(x01(1,i)+sep*0.5));
  yrq2=[yrq2,yrq2(1,i)+k2];
end
plot(x01,yrq2)
yrq4=[4]
for i=1:length(x01)-1
   k1=sep*yrq4(1,i)*(-f(x01(1,i)));
   k2=sep*(yrq4(1,i)+0.5*k1)*(-f(x01(1,i)+sep*0.5));
   k3=sep*(yrq4(1,i)+0.5*k2)*(-f(x01(1,i)+sep*0.5));
   k4=sep*(yrq4(1,i)+k1)*(-f(x01(1,i)+sep));
   yrq4=[yrq4,yrq4(1,i)+1/6*k1+1/3*(k2+k3)+1/6*k4];
end
plot(x01,yrq4,"*")

