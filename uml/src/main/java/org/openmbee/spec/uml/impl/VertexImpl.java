package org.openmbee.spec.uml.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.ManyToAny;
import org.openmbee.spec.uml.Classifier;
import org.openmbee.spec.uml.Comment;
import org.openmbee.spec.uml.Dependency;
import org.openmbee.spec.uml.Element;
import org.openmbee.spec.uml.Namespace;
import org.openmbee.spec.uml.RedefinableElement;
import org.openmbee.spec.uml.Region;
import org.openmbee.spec.uml.StringExpression;
import org.openmbee.spec.uml.Transition;
import org.openmbee.spec.uml.Vertex;
import org.openmbee.spec.uml.VisibilityKind;
import org.openmbee.spec.uml.jackson.MofObjectDeserializer;
import org.openmbee.spec.uml.jackson.MofObjectSerializer;

@PrimaryKeyJoinColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VertexImpl extends MofObjectImpl implements Vertex {

    private Collection<RedefinableElement> redefinedElement;
    private Element owner;
    private Boolean isLeaf;
    private VisibilityKind visibility;
    private Collection<Element> ownedElement;
    private Collection<Transition> incoming;
    private String qualifiedName;
    private Vertex redefinedVertex;
    private String name;
    private Namespace namespace;
    private Collection<Classifier> redefinitionContext;
    private Collection<Dependency> clientDependency;
    private StringExpression nameExpression;
    private Collection<Transition> outgoing;
    private Collection<Comment> ownedComment;
    private Region container;

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
    @Column(name = "isLeaf", table = "Vertex")
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
    @Transient
    public Collection<Transition> getIncoming() {
        if (incoming == null) {
            incoming = new ArrayList<>();
        }
        return incoming;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TransitionImpl.class)
    public void setIncoming(Collection<Transition> incoming) {
        this.incoming = incoming;
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
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "VertexMetaDef", metaColumn = @Column(name = "redefinedVertexType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "redefinedVertexId", table = "Vertex")
    public Vertex getRedefinedVertex() {
        return redefinedVertex;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = VertexImpl.class)
    public void setRedefinedVertex(Vertex redefinedVertex) {
        this.redefinedVertex = redefinedVertex;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", table = "Vertex")
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
    @JoinColumn(name = "nameExpressionId", table = "Vertex")
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
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Transition> getOutgoing() {
        if (outgoing == null) {
            outgoing = new ArrayList<>();
        }
        return outgoing;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TransitionImpl.class)
    public void setOutgoing(Collection<Transition> outgoing) {
        this.outgoing = outgoing;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @Column(name = "ownedCommentType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Vertex_ownedComment",
        joinColumns = @JoinColumn(name = "VertexId"),
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

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RegionMetaDef", metaColumn = @Column(name = "containerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "containerId", table = "Vertex")
    public Region getContainer() {
        return container;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RegionImpl.class)
    public void setContainer(Region container) {
        this.container = container;
    }

}
