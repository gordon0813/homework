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
   re1=real(x);
   im1=imag(x);
   axisx=linspace(0,2*pi,1387);
   k=[[0:1:15],[0],[-15:1:-1]];
   re=re1(1,1)*cos(k(1,1)*axisx)-im1(1,1)*sin(k(1,1)*axisx);
   for j= 2:32
     re=re+re1(1,j)*cos(k(1,j)*axisx)-im1(1,j)*sin(k(1,j)*axisx);
   endfor
   re=re/32
endfunction
axisx=linspace(0,2*pi,1387);
n32=linspace(0,2*pi,33);
x32=remove_boundry_s(n32);
yf=f(x32);
yg=g(x32);
func1=get_real_func(fft(yf.*yg));
testt=fft(yf.*yg)
fk=fft(yf);
gk=fft(yg);
indexf=[fk,fk,fk];
indexg=[gk,gk,gk];
hm=[]
for i =1:32
  for j=1:32
    hm(i,j)=indexf(1,j+32)*indexg(1,i-j+32);
  endfor
endfor

hm=sum(hm);

func2=get_real_func(hm)#1,[[17:32],[1:16]])


real_func=f(axisx).*g(axisx);

figure(1);
plot(axisx,func1,"r")#C.1
hold
plot(axisx,real_func,"b")#C.3
#plot(axisx,func2,"g")
plot(x32,f(x32).*g(x32),"*") #¸ê®ÆÂI
#figure(2);
#plot([1:1:32],abs(hm),"r")
#figure(3)
#plot([1:1:32],abs(testt),"b-*")