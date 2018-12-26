clear all 
clc
x=linspace(1,-3,2000);
y=linspace(3,-3,20000);
function f=boundary2(x,y)
  img=x+i*y;
  ref=abs(1+img+img.^2/2);
  ref(ref>1)=1;
  ref(ref<1)=0;
  ref=ref-[ref(1,2:length(ref)),0];
  ref(1,1)=0;
  ref(1,length(ref))=0;
  f=x(find(ref));
end
ans=boundary2(x,1.73);
xplot=[];
yplot=[];
for i=1:length(y)
  temp=boundary2(x,y(1,i));
  if(length(temp)>0)
    xplot=[xplot,temp(1,1),temp(1,2)];
    yplot=[yplot,y(1,i),y(1,i)];
  endif
  aaa="ok"
end

function f=boundary4(x,y)
  img=x+i*y;
  ref=abs(1+img+img.^2/2+img.^3/6+img.^4/24);
  ref(ref>1)=1;
  ref(ref<1)=0;
  ref=ref-[ref(1,2:length(ref)),0];
  ref(1,1)=0;
  ref(1,length(ref))=0;
  f=x(find(ref));
end
xplot1=[];
yplot1=[];
for i=1:length(y)
  temp=boundary4(x,y(1,i));
  if(length(temp)>0)
    xplot1=[xplot1,temp(1,1),temp(1,2)];
    yplot1=[yplot1,y(1,i),y(1,i)];
  endif
  aaa="ok"
end
plot(xplot,yplot,"*")
hold
plot(xplot1,yplot1,"*")
