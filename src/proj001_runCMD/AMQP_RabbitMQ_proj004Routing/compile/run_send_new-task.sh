#set an environment variable for the classpath
export CP=.:commons-io-1.2.jar:commons-cli-1.1.jar:rabbitmq-client.jar

#RUN
java -cp $CP _1producer_NewTask error "Run. Run. Or it will explode."
java -cp $CP _1producer_NewTask warning "Run. Run. Or it will go yellow."
java -cp $CP _1producer_NewTask success "Run. Run. Or it will run dollars."


#DISPLAY MESSAGES IN QUEUE
#echo ''
#echo 'list_exchanges ##########'
#sudo rabbitmqctl list_exchanges

#echo ''
#echo 'list_queues ##########'
#sudo rabbitmqctl list_queues name messages_ready messages_unacknowledged

#echo ''
#echo 'list_bindings ##########'
#sudo rabbitmqctl list_bindings