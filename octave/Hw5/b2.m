clear all 
clc
function re=f(x) 
  re=2+0.01*x^2
end
function re=g(x)
  re=4*exp(-0.003333*x.^3-2*x)
end
broke_1=[]
broke_2=[]
broke_3=[]
broke_4=[]
broke_5=[]
xb=[]
for j=10:300;
sep=j*0.01
x01=[0:sep:15]
xb=[xb,j]

y=[4]
for i=1:length(x01);
  y=[y,y(1,i)*(1-sep*f(x01(1,i)))];
end
y(y>4.2)=4.2
y(y<-1)=-5
if(abs(y(int16(14/sep)))>=4.1)
broke_1=[broke_1,1]
else
broke_1=[broke_1,0]
endif
yback=[4];
for i=1:length(x01)-1;
  yback=[yback,yback(1,i)/(1+sep*f(x01(1,i+1)))];
end;
yback(yback>4.2)=4.2;
yback(yback<-1)=-5;
if(abs(yback(int16(14/sep)))>=4.1)
broke_2=[broke_2,1];
else
broke_2=[broke_2,0];
endif
yt=[4]
for i=1:length(x01)-1;
  yt=[yt,(yt(1,i)-sep*yt(1,i)*1/2*f(x01(1,i)))/(1+1/2*sep*f(x01(1,i+1)))];
end
yt(yt>4.2)=4.2;
yt(yt<-1)=-5;
if(abs(yt(int16(14/sep)))>=4.1)
broke_3=[broke_3,1];
else
broke_3=[broke_3,0];
endif
yrq2=[4];
for i=1:length(x01)-1
  k1=sep*yrq2(1,i)*(-f(x01(1,i)));
  k2=sep*(yrq2(1,i)+0.5*k1)*(-f(x01(1,i)+sep*0.5));
  yrq2=[yrq2,yrq2(1,i)+k2];
end
yrq2(yrq2>4.2)=4.2;
yrq2(yrq2<-1)=-5;
if(abs(yrq2(int16(14/sep)))>=4.1)
broke_4=[broke_4,1];
else
broke_4=[broke_4,0];
endif
yrq4=[4]
for i=1:length(x01)-1
   k1=sep*yrq4(1,i)*(-f(x01(1,i)));
   k2=sep*(yrq4(1,i)+0.5*k1)*(-f(x01(1,i)+sep*0.5));
   k3=sep*(yrq4(1,i)+0.5*k2)*(-f(x01(1,i)+sep*0.5));
   k4=sep*(yrq4(1,i)+k1)*(-f(x01(1,i)+sep));
   yrq4=[yrq4,yrq4(1,i)+1/6*k1+1/3*(k2+k3)+1/6*k4];
end
yrq4(yrq4>4.2)=4.2
yrq4(yrq4<-1)=-5
if(abs(yrq4(int16(14/sep)))>=4.1)
broke_5=[broke_5,1]
else
broke_5=[broke_5,0]
endif
endfor
plot(xb,broke_1,"r")
hold
plot(xb,broke_2,"g")
plot(xb,broke_3,"b")
plot(xb,broke_4,"k")
plot(xb,broke_5,"*")