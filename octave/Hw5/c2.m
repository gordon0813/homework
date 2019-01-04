clear all 
clc
f1b=1
f1a=0.5
f2=1.0427
f2b=1.6552
f3=0
sep=0.002
x=[1:sep:10];

while (abs(abs(f2)-1)>0.00001)
  f1copy=f1a
  f1a=f1a+((f1a-f1b)/(f2-f2b))*(1-f2);
  f1b=f1copy
  
  f2b=f2; 
  f1=f1a
  f2=0;
  f3=0;
for i =1:length(x);
  f1k1=sep*(-f1*f3);
  f2k1= sep*f1;
  f3k1=sep*f2;
  f1k2=sep*(-(f1+0.5*f1k1)*(f3+0.5*f3k1));
  f2k2=sep*(f1+0.5*f1k1);
  f3k2=sep*(f2+0.5*f2k1);
  f1k3=sep*(-(f1+0.5*f1k2)*(f3+0.5*f3k2));
  f2k3=sep*(f1+0.5*f1k2);
  f3k3=sep*(f2+0.5*f2k2);
  f1k4=sep*(-(f1+f1k3)*(f3+f3k3));
  f2k4=sep*(f1+f1k3);
  f3k4=sep*(f2+f2k3);
  f1=f1+1/6*f1k1+1/3*(f1k2+f1k3)+1/6*f1k4;
  f2=f2+1/6*f2k1+1/3*(f2k2+f2k3)+1/6*f2k4;
  f3=f3+1/6*f3k1+1/3*(f3k2+f3k3)+1/6*f3k4;
endfor
endwhile
x=[1:sep:10];

f1=f1copy
f2=0
f3=0
y1=[]
y2=[]
y3=[]
for i =1:length(x);
  f1k1=sep*(-f1*f3);
  f2k1= sep*f1;
  f3k1=sep*f2;
  f1k2=sep*(-(f1+0.5*f1k1)*(f3+0.5*f3k1));
  f2k2=sep*(f1+0.5*f1k1);
  f3k2=sep*(f2+0.5*f2k1);
  f1k3=sep*(-(f1+0.5*f1k2)*(f3+0.5*f3k2));
  f2k3=sep*(f1+0.5*f1k2);
  f3k3=sep*(f2+0.5*f2k2);
  f1k4=sep*(-(f1+f1k3)*(f3+f3k3));
  f2k4=sep*(f1+f1k3);
  f3k4=sep*(f2+f2k3);
  f1=f1+1/6*f1k1+1/3*(f1k2+f1k3)+1/6*f1k4;
  f2=f2+1/6*f2k1+1/3*(f2k2+f2k3)+1/6*f2k4;
  f3=f3+1/6*f3k1+1/3*(f3k2+f3k3)+1/6*f3k4;
  y1=[y1,f1];
  y2=[y2,f2];
  y3=[y3,f3];
endfor
figure(1)
plot(x,y1)
figure(2)
plot(x,y2)
figure(3)
plot(x,y3)



