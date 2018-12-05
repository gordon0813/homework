clear all
clc
function f=central(x,h)
   h
   L=size(x)(2);
   f=(x(1,3:L)-x(1,1:L-2))/(2*h);
endfunction
function f1=f(x);
  f1=sin(3*x)+3*cos(6*x);
endfunction
function f1=realdf(x);
  f1=3*cos(3*x)-18*sin(6*x);
endfunction
function re=remove_boundry(x)
  L=size(x)(2);
  re=x(1,2:L-1);
endfunction
function re=remove_boundry_s(x)
  L=size(x)(2);
  re=x(1,1:L-1);
endfunction
realx=linspace(0,2*pi,1387);


n16=linspace(0,2*pi,17);
n32=linspace(0,2*pi,33);
x16=remove_boundry(n16);
x32=remove_boundry(n32);

yc16=central(f(n16),n16(1,2)-n16(1,1));
yc32=central(f(n32),n32(1,2)-n32(1,1));

fx16=remove_boundry_s(n16);
fx32=remove_boundry_s(n32);
y16=fft(f(fx16));
y32=fft(f(fx32));
yf16=real(ifft((y16.*[[0:1:7],[0],[-7:1:-1]])*1i));
yf32=real(ifft((y32.*[[0:1:15],[0],[-15:1:-1]])*1i));

yreal=realdf(realx);

figure(1)
plot(x16,yc16,"r-*")
hold 
plot(realx,yreal,"g")
plot(fx16,yf16,"k-*")
figure(2)
plot(x32,yc32,"r-*")
hold
plot(realx,yreal,"g")
plot(fx32,yf32,"k-*")