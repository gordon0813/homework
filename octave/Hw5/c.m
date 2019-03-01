clear all 
clc
# http://www.csun.edu/~lcaretto/me309/21-nsodeSystems.pdf      (ODE System by RK4)
function f=boundary2(x,y)
  ref=y
  ref(ref>1)=1;
  ref(ref<1)=0;
  ref=ref-[ref(1,2:length(ref)),0];
  ref(1,1)=0;
  ref(1,length(ref))=0;
  f=x(find(ref));
end
f1=[0.1:0.001:1];

f1copy=f1;
f2=f1*0;
f2copy=f2;
f3=f1*0;
f3copy=f3;
sep=0.002
x=[1:sep:10];
for i =1:length(x);
  f1k1=sep*(-f1.*f3);
  f2k1=sep*f1;
  f3k1=sep*f2;
  f1k2=sep*(-(f1+0.5*f1k1).*(f3+0.5*f3k1));
  f2k2=sep*(f1+0.5*f1k1);
  f3k2=sep*(f2+0.5*f2k1);
  f1k3=sep*(-(f1+0.5*f1k2).*(f3+0.5*f3k2));
  f2k3=sep*(f1+0.5*f1k2);
  f3k3=sep*(f2+0.5*f2k2);
  f1k4=sep*(-(f1+f1k3).*(f3+f3k3));
  f2k4=sep*(f1+f1k3);
  f3k4=sep*(f2+f2k3);
  f1=f1+1/6*f1k1+1/3*(f1k2+f1k3)+1/6*f1k4;
  f2=f2+1/6*f2k1+1/3*(f2k2+f2k3)+1/6*f2k4;
  f3=f3+1/6*f3k1+1/3*(f3k2+f3k3)+1/6*f3k4;
 
  
end
plot(f1copy,f2)
b=boundary2(f1copy,f2)

