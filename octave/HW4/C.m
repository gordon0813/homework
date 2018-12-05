clear all
clc
function f1=f(x);
  f1=sin(2*x)+0.1*sin(15*x);
endfunction
function f1=g(x);
  f1=sin(2*x)+0.1*cos(15*x);
endfunction
function re=remove_boundry_s(x)
  L=size(x)(2);
  re=x(1,1:L-1);
endfunction
function re=get_real_func(x)
   re1=real(x)
   im1=imag(x)
   axisx=linspace(0,2*pi,1387)
   k=[[0:1:15],[0],[-15:1:-1]]
   re=re1(1,1)*cos(k(1,1)*axisx)-im1(1,1)*sin(k(1,1)*axisx)
   for j= 2:32
     re=re+re1(1,j)*cos(k(1,j)*axisx)-im1(1,j)*sin(k(1,j)*axisx)
   endfor
   re=re/32
endfunction
axisx=linspace(0,2*pi,1387)
n32=linspace(0,2*pi,33);
x32=remove_boundry_s(n32);
yf=f(x32)
yg=g(x32)
func1=get_real_func(fft(yf.*yg))

real_func=f(axisx).*g(axisx)
figure(1)
plot(axisx,func1,"r")
figure(2)
plot(axisx,real_func,"b")