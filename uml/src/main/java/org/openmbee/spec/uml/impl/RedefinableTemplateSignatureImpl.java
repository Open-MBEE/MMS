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
import org.openmbee.spec.uml.Classifier;
import org.openmbee.spec.uml.Comment;
import org.openmbee.spec.uml.Dependency;
import org.openmbee.spec.uml.Element;
import org.openmbee.spec.uml.Namespace;
import org.openmbee.spec.uml.RedefinableElement;
import org.openmbee.spec.uml.RedefinableTemplateSignature;
import org.openmbee.spec.uml.StringExpression;
import org.openmbee.spec.uml.TemplateParameter;
import org.openmbee.spec.uml.TemplateableElement;
import org.openmbee.spec.uml.VisibilityKind;
import org.openmbee.spec.uml.jackson.MofObjectDeserializer;
import org.openmbee.spec.uml.jackson.MofObjectSerializer;

@Entity
@SecondaryTable(name = "RedefinableTemplateSignature")
@Table(appliesTo = "RedefinableTemplateSignature", fetch = FetchMode.SELECT, optional = false)
@DiscriminatorValue(value = "RedefinableTemplateSignature")
@JsonTypeName(value = "RedefinableTemplateSignature")
public class RedefinableTemplateSignatureImpl extends MofObjectImpl implements
    RedefinableTemplateSignature {

    private Element owner;
    private Collection<RedefinableElement> redefinedElement;
    private Boolean isLeaf;
    private Collection<RedefinableTemplateSignature> extendedSignature;
    private VisibilityKind visibility;
    private Collection<Element> ownedElement;
    private Collection<TemplateParameter> inheritedParameter;
    private TemplateableElement template;
    private String qualifiedName;
    private String name;
    private List<TemplateParameter> ownedParameter;
    private Namespace namespace;
    private Collection<Classifier> redefinitionContext;
    private Collection<Dependency> clientDependency;
    private StringExpression nameExpression;
    private Classifier classifier;
    private List<TemplateParameter> parameter;
    private Collection<Comment> ownedComment;

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
    @Column(name = "isLeaf", table = "RedefinableTemplateSignature")
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
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RedefinableTemplateSignatureMetaDef", metaColumn = @Column(name = "extendedSignatureType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RedefinableTemplateSignature_extendedSignature",
        joinColumns = @JoinColumn(name = "RedefinableTemplateSignatureId"),
        inverseJoinColumns = @JoinColumn(name = "extendedSignatureId"))
    public Collection<RedefinableTemplateSignature> getExtendedSignature() {
        if (extendedSignature == null) {
            extendedSignature = new ArrayList<>();
        }
        return extendedSignature;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RedefinableTemplateSignatureImpl.class)
    public void setExtendedSignature(Collection<RedefinableTemplateSignature> extendedSignature) {
        this.extendedSignature = extendedSignature;
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
    public Collection<TemplateParameter> getInheritedParameter() {
        if (inheritedParameter == null) {
            inheritedParameter = new ArrayList<>();
        }
        return inheritedParameter;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TemplateParameterImpl.class)
    public void setInheritedParameter(Collection<TemplateParameter> inheritedParameter) {
        this.inheritedParameter = inheritedParameter;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "TemplateableElementMetaDef", metaColumn = @Column(name = "templateType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "templateId", table = "RedefinableTemplateSignature")
    public TemplateableElement getTemplate() {
        return template;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = TemplateableElementImpl.class)
    public void setTemplate(TemplateableElement template) {
        this.template = template;
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
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", table = "RedefinableTemplateSignature")
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
    @ManyToAny(metaDef = "TemplateParameterMetaDef", metaColumn = @Column(name = "ownedParameterType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RedefinableTemplateSignature_ownedParameter",
        joinColumns = @JoinColumn(name = "RedefinableTemplateSignatureId"),
        inverseJoinColumns = @JoinColumn(name = "ownedParameterId"))
    public List<TemplateParameter> getOwnedParameter() {
        if (ownedParameter == null) {
            ownedParameter = new ArrayList<>();
        }
        return ownedParameter;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TemplateParameterImpl.class)
    public void setOwnedParameter(List<TemplateParameter> ownedParameter) {
        this.ownedParameter = ownedParameter;
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
    @JoinColumn(name = "nameExpressionId", table = "RedefinableTemplateSignature")
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
    @Any(metaDef = "ClassifierMetaDef", metaColumn = @Column(name = "classifierType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "classifierId", table = "RedefinableTemplateSignature")
    public Classifier getClassifier() {
        return classifier;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ClassifierImpl.class)
    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "TemplateParameterMetaDef", metaColumn = @Column(name = "parameterType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RedefinableTemplateSignature_parameter",
        joinColumns = @JoinColumn(name = "RedefinableTemplateSignatureId"),
        inverseJoinColumns = @JoinColumn(name = "parameterId"))
    public List<TemplateParameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return parameter;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TemplateParameterImpl.class)
    public void setParameter(List<TemplateParameter> parameter) {
        this.parameter = parameter;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @Column(name = "ownedCommentType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RedefinableTemplateSignature_ownedComment",
        joinColumns = @JoinColumn(name = "RedefinableTemplateSignatureId"),
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
