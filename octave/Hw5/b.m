clear all 
clc
function re=f(x) 
  re=2+0.01*x^2
end
function re=g(x)
  re=4*exp(-0.003333*x.^3-2*x)
end
sep=1
x01=[0:sep:15]


y=[4]
for i=1:length(x01);
  y=[y,y(1,i)*(1-sep*f(x01(1,i)))];
end
y(y>4.2)=4.2
y(y<-1)=-1
plot(x01,y(1:length(y)-1),"r");
hold;
yback=[4];
for i=1:length(x01)-1;
  yback=[yback,yback(1,i)/(1+sep*f(x01(1,i+1)))];
end;
yback(yback>4.2)=4.2
yback(yback<-1)=-1
plot(x01,yback,"g");
yt=[4]
for i=1:length(x01)-1;
  yt=[yt,(yt(1,i)-sep*yt(1,i)*1/2*f(x01(1,i)))/(1+1/2*sep*f(x01(1,i+1)))];
end
yt(yt>4.2)=4.2
yt(yt<-1)=-1
plot(x01,yt,"b");
yrq2=[4];
for i=1:length(x01)-1
  k1=sep*yrq2(1,i)*(-f(x01(1,i)));
  k2=sep*(yrq2(1,i)+0.5*k1)*(-f(x01(1,i)+sep*0.5));
  yrq2=[yrq2,yrq2(1,i)+k2];
end
yrq2(yrq2>4.2)=4.2
yrq2(yrq2<-1)=-1
plot(x01,yrq2,"c ")
yrq4=[4]
for i=1:length(x01)-1
   k1=sep*yrq4(1,i)*(-f(x01(1,i)));
   k2=sep*(yrq4(1,i)+0.5*k1)*(-f(x01(1,i)+sep*0.5));
   k3=sep*(yrq4(1,i)+0.5*k2)*(-f(x01(1,i)+sep*0.5));
   k4=sep*(yrq4(1,i)+k3)*(-f(x01(1,i)+sep));
   yrq4=[yrq4,yrq4(1,i)+1/6*k1+1/3*(k2+k3)+1/6*k4];
end
yrq4(yrq4>4.2)=4.2
yrq4(yrq4<-1)=-1
plot(x01,yrq4,"k")
plot(x01,g(x01),"*")

