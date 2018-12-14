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
   re=re
endfunction

axisx=linspace(0,2*pi,1387);
n32=linspace(0,2*pi,33);
x32=remove_boundry_s(n32);
yf=f(x32);
yg=g(x32);
func1=get_real_func(fft(yf.*yg/32));
testt=fft(yf.*yg/32)
fk=fft(yf)/32;
gk=fft(yg)/32;
indexf=[fk,fk](1,17:48);
indexg=[gk,gk](1,17:48);
hmm=[]
for i =1:32
  for j=1:32
    if(i-j>=-16 && 15>=i-j)
      hmm(j,i)=indexf(1,j)*indexg(1,i-j+17);
    endif
  endfor
endfor
hm=sum(hmm);
 hm = [ hm(1,17:32),hm(1,1:16) ]
func2=get_real_func(hm)#1,[[17:32],[1:16]])
real_func=f(axisx).*g(axisx);
figure(1);
#plot(axisx,func1,"r")#C.1
hold
plot(axisx,real_func,"b")#C.3
plot(axisx,func2,"r")
plot(x32,f(x32).*g(x32),"*") #¸ê®ÆÂI
figure(2);
plot([1:1:32],real(hm),"r-*")
hold
plot([1:1:32],real(testt),"b-*")