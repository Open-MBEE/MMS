package org.openmbee.spec.uml.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.SecondaryTable;
import javax.persistence.Transient;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Table;
import org.openmbee.spec.uml.Activity;
import org.openmbee.spec.uml.ActivityEdge;
import org.openmbee.spec.uml.ActivityGroup;
import org.openmbee.spec.uml.ActivityNode;
import org.openmbee.spec.uml.ActivityPartition;
import org.openmbee.spec.uml.Classifier;
import org.openmbee.spec.uml.Comment;
import org.openmbee.spec.uml.Constraint;
import org.openmbee.spec.uml.Dependency;
import org.openmbee.spec.uml.Element;
import org.openmbee.spec.uml.ExceptionHandler;
import org.openmbee.spec.uml.InputPin;
import org.openmbee.spec.uml.InterruptibleActivityRegion;
import org.openmbee.spec.uml.Namespace;
import org.openmbee.spec.uml.OutputPin;
import org.openmbee.spec.uml.Port;
import org.openmbee.spec.uml.RedefinableElement;
import org.openmbee.spec.uml.SendObjectAction;
import org.openmbee.spec.uml.StringExpression;
import org.openmbee.spec.uml.StructuredActivityNode;
import org.openmbee.spec.uml.VisibilityKind;
import org.openmbee.spec.uml.jackson.MofObjectDeserializer;
import org.openmbee.spec.uml.jackson.MofObjectSerializer;

@Entity
@SecondaryTable(name = "SendObjectAction")
@Table(appliesTo = "SendObjectAction", fetch = FetchMode.SELECT, optional = false)
@DiscriminatorValue(value = "SendObjectAction")
@JsonTypeName(value = "SendObjectAction")
public class SendObjectActionImpl extends MofObjectImpl implements SendObjectAction {

    private Collection<RedefinableElement> redefinedElement;
    private Element owner;
    private Port onPort;
    private VisibilityKind visibility;
    private Collection<InterruptibleActivityRegion> inInterruptibleRegion;
    private List<InputPin> argument;
    private Collection<ActivityGroup> inGroup;
    private String name;
    private Collection<Constraint> localPrecondition;
    private Namespace namespace;
    private Classifier context;
    private List<InputPin> input;
    private Collection<Dependency> clientDependency;
    private StringExpression nameExpression;
    private StructuredActivityNode inStructuredNode;
    private Activity activity;
    private Collection<Constraint> localPostcondition;
    private Collection<ActivityNode> redefinedNode;
    private Boolean isLeaf;
    private Boolean isLocallyReentrant;
    private List<OutputPin> output;
    private Collection<ExceptionHandler> handler;
    private Collection<Element> ownedElement;
    private Collection<ActivityEdge> outgoing;
    private String qualifiedName;
    private Collection<ActivityPartition> inPartition;
    private Collection<ActivityEdge> incoming;
    private Collection<Classifier> redefinitionContext;
    private InputPin target;
    private Collection<Comment> ownedComment;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<RedefinableElement> getRedefinedElement() {
        if (redefinedElement == null) {
            redefinedElement = new ArrayList<>();
        }
        return redefinedElement;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RedefinableElementImpl.class)
    public void setRedefinedElement(Collection<RedefinableElement> redefinedElement) {
        this.redefinedElement = redefinedElement;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Transient
    public Element getOwner() {
        return owner;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "PortMetaDef", metaColumn = @Column(name = "onPortType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "onPortId", table = "SendObjectAction")
    public Port getOnPort() {
        return onPort;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PortImpl.class)
    public void setOnPort(Port onPort) {
        this.onPort = onPort;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Enumerated(EnumType.STRING)
    public VisibilityKind getVisibility() {
        return visibility;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setVisibility(VisibilityKind visibility) {
        this.visibility = visibility;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "InterruptibleActivityRegionMetaDef", metaColumn = @Column(name = "inInterruptibleRegionType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_inInterruptibleRegion",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "inInterruptibleRegionId"))
    public Collection<InterruptibleActivityRegion> getInInterruptibleRegion() {
        if (inInterruptibleRegion == null) {
            inInterruptibleRegion = new ArrayList<>();
        }
        return inInterruptibleRegion;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = InterruptibleActivityRegionImpl.class)
    public void setInInterruptibleRegion(
        Collection<InterruptibleActivityRegion> inInterruptibleRegion) {
        this.inInterruptibleRegion = inInterruptibleRegion;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "InputPinMetaDef", metaColumn = @Column(name = "argumentType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_argument",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "argumentId"))
    public List<InputPin> getArgument() {
        if (argument == null) {
            argument = new ArrayList<>();
        }
        return argument;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = InputPinImpl.class)
    public void setArgument(List<InputPin> argument) {
        this.argument = argument;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<ActivityGroup> getInGroup() {
        if (inGroup == null) {
            inGroup = new ArrayList<>();
        }
        return inGroup;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityGroupImpl.class)
    public void setInGroup(Collection<ActivityGroup> inGroup) {
        this.inGroup = inGroup;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", table = "SendObjectAction")
    public String getName() {
        return name;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ConstraintMetaDef", metaColumn = @Column(name = "localPreconditionType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_localPrecondition",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "localPreconditionId"))
    public Collection<Constraint> getLocalPrecondition() {
        if (localPrecondition == null) {
            localPrecondition = new ArrayList<>();
        }
        return localPrecondition;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConstraintImpl.class)
    public void setLocalPrecondition(Collection<Constraint> localPrecondition) {
        this.localPrecondition = localPrecondition;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Transient
    public Namespace getNamespace() {
        return namespace;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = NamespaceImpl.class)
    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Transient
    public Classifier getContext() {
        return context;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ClassifierImpl.class)
    public void setContext(Classifier context) {
        this.context = context;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public List<InputPin> getInput() {
        if (input == null) {
            input = new ArrayList<>();
        }
        return input;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = InputPinImpl.class)
    public void setInput(List<InputPin> input) {
        this.input = input;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Dependency> getClientDependency() {
        if (clientDependency == null) {
            clientDependency = new ArrayList<>();
        }
        return clientDependency;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = DependencyImpl.class)
    public void setClientDependency(Collection<Dependency> clientDependency) {
        this.clientDependency = clientDependency;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "StringExpressionMetaDef", metaColumn = @Column(name = "nameExpressionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "nameExpressionId", table = "SendObjectAction")
    public StringExpression getNameExpression() {
        return nameExpression;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = StringExpressionImpl.class)
    public void setNameExpression(StringExpression nameExpression) {
        this.nameExpression = nameExpression;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "StructuredActivityNodeMetaDef", metaColumn = @Column(name = "inStructuredNodeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "inStructuredNodeId", table = "SendObjectAction")
    public StructuredActivityNode getInStructuredNode() {
        return inStructuredNode;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = StructuredActivityNodeImpl.class)
    public void setInStructuredNode(StructuredActivityNode inStructuredNode) {
        this.inStructuredNode = inStructuredNode;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ActivityMetaDef", metaColumn = @Column(name = "activityType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", table = "SendObjectAction")
    public Activity getActivity() {
        return activity;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ActivityImpl.class)
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ConstraintMetaDef", metaColumn = @Column(name = "localPostconditionType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_localPostcondition",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "localPostconditionId"))
    public Collection<Constraint> getLocalPostcondition() {
        if (localPostcondition == null) {
            localPostcondition = new ArrayList<>();
        }
        return localPostcondition;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConstraintImpl.class)
    public void setLocalPostcondition(Collection<Constraint> localPostcondition) {
        this.localPostcondition = localPostcondition;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityNodeMetaDef", metaColumn = @Column(name = "redefinedNodeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_redefinedNode",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "redefinedNodeId"))
    public Collection<ActivityNode> getRedefinedNode() {
        if (redefinedNode == null) {
            redefinedNode = new ArrayList<>();
        }
        return redefinedNode;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityNodeImpl.class)
    public void setRedefinedNode(Collection<ActivityNode> redefinedNode) {
        this.redefinedNode = redefinedNode;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Column(name = "isLeaf", table = "SendObjectAction")
    public Boolean isLeaf() {
        return isLeaf;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Column(name = "isLocallyReentrant", table = "SendObjectAction")
    public Boolean isLocallyReentrant() {
        return isLocallyReentrant;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setLocallyReentrant(Boolean isLocallyReentrant) {
        this.isLocallyReentrant = isLocallyReentrant;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public List<OutputPin> getOutput() {
        if (output == null) {
            output = new ArrayList<>();
        }
        return output;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = OutputPinImpl.class)
    public void setOutput(List<OutputPin> output) {
        this.output = output;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ExceptionHandlerMetaDef", metaColumn = @Column(name = "handlerType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_handler",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "handlerId"))
    public Collection<ExceptionHandler> getHandler() {
        if (handler == null) {
            handler = new ArrayList<>();
        }
        return handler;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ExceptionHandlerImpl.class)
    public void setHandler(Collection<ExceptionHandler> handler) {
        this.handler = handler;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Element> getOwnedElement() {
        if (ownedElement == null) {
            ownedElement = new ArrayList<>();
        }
        return ownedElement;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedElement(Collection<Element> ownedElement) {
        this.ownedElement = ownedElement;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityEdgeMetaDef", metaColumn = @Column(name = "outgoingType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_outgoing",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "outgoingId"))
    public Collection<ActivityEdge> getOutgoing() {
        if (outgoing == null) {
            outgoing = new ArrayList<>();
        }
        return outgoing;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityEdgeImpl.class)
    public void setOutgoing(Collection<ActivityEdge> outgoing) {
        this.outgoing = outgoing;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Transient
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityPartitionMetaDef", metaColumn = @Column(name = "inPartitionType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_inPartition",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "inPartitionId"))
    public Collection<ActivityPartition> getInPartition() {
        if (inPartition == null) {
            inPartition = new ArrayList<>();
        }
        return inPartition;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityPartitionImpl.class)
    public void setInPartition(Collection<ActivityPartition> inPartition) {
        this.inPartition = inPartition;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityEdgeMetaDef", metaColumn = @Column(name = "incomingType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_incoming",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "incomingId"))
    public Collection<ActivityEdge> getIncoming() {
        if (incoming == null) {
            incoming = new ArrayList<>();
        }
        return incoming;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityEdgeImpl.class)
    public void setIncoming(Collection<ActivityEdge> incoming) {
        this.incoming = incoming;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Classifier> getRedefinitionContext() {
        if (redefinitionContext == null) {
            redefinitionContext = new ArrayList<>();
        }
        return redefinitionContext;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ClassifierImpl.class)
    public void setRedefinitionContext(Collection<Classifier> redefinitionContext) {
        this.redefinitionContext = redefinitionContext;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "InputPinMetaDef", metaColumn = @Column(name = "targetType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "targetId", table = "SendObjectAction")
    public InputPin getTarget() {
        return target;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = InputPinImpl.class)
    public void setTarget(InputPin target) {
        this.target = target;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @Column(name = "ownedCommentType"), fetch = FetchType.LAZY)
    @JoinTable(name = "SendObjectAction_ownedComment",
        joinColumns = @JoinColumn(name = "SendObjectActionId"),
        inverseJoinColumns = @JoinColumn(name = "ownedCommentId"))
    public Collection<Comment> getOwnedComment() {
        if (ownedComment == null) {
            ownedComment = new ArrayList<>();
        }
        return ownedComment;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CommentImpl.class)
    public void setOwnedComment(Collection<Comment> ownedComment) {
        this.ownedComment = ownedComment;
    }

}
